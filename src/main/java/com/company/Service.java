package com.company;


import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Service {
    private final String channelUsername;
    private final int regionCode;
    private final int perPage;
    private final static String URL_ALL_VACANCIES = "https://ishapi.mehnat.uz/api/v1/vacancies?page=1&per_page=%d&sort_key=created_at&company_soato_code=%d";
    private final static String BASE_URL = "https://ishapi.mehnat.uz/api/v1/vacancies/";
    private Integer lastId = 0;
    private static final NotificationBot notificationBot = new NotificationBot();
    private static final ReporterBot reporterBot = new ReporterBot();
    private final TelegramBot bot;

    public Service(String channelUsername, Integer regionCode, Integer perPage) {
        this.regionCode = regionCode;
        this.perPage = perPage;
        this.channelUsername= channelUsername;
        bot = new TelegramBot(channelUsername);
    }

    public void run() {
        List<Integer> ids = new ArrayList<>();
        try {
            ids = getIds();
        } catch (IOException e) {
            notificationBot.sendMessage("Exception : Bo'g'lanishda xatolik, vakansiyalarni olib bo'lmadi");
        }

        List<Vacancy> vacancies = getVacancies(ids);
        try {
            sendMessage(vacancies);
        } catch (IOException | InterruptedException e) {
            notificationBot.sendMessage("Exception bo'ldi : " + Arrays.toString(e.getStackTrace()));
        }
    }

    private void sendMessage(List<Vacancy> vacancies) throws IOException, InterruptedException {
        int countMessages = 0;
        for (Vacancy vacancy : vacancies) {
            String text;
            text = message(vacancy, bot.getBotUsername().substring(1));
            bot.sendMessageToChannel(text);
            Thread.sleep(7000);
            countMessages++;
        }
        if (vacancies.size() != 0) {
            String s = "\uD83D\uDCDD 10-minutlik hisobot\n\n" + vacancies.get(0).getRegion() + " kanaliga " + countMessages + " ta vakansiya joylandi";
            reporterBot.sendMessage(s);
        }
    }

    private List<Integer> getIds() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = mapper.readTree(new URL(URL_ALL_VACANCIES.formatted(perPage, regionCode)));
        JsonNode data = node.get("data");
        ArrayNode vacancyList = (ArrayNode) data.get("data");
        List<Integer> ids = new ArrayList<>();
        for (int i = 0; i < vacancyList.size(); i++) {
            int id = vacancyList.get(i).get("id").asInt(0);
            if (id > this.lastId)
                ids.add(id);
            else {
                break;
            }
        }
        if(ids.size()!=0)
            lastId = ids.get(0);
        return ids;
    }

    private List<Vacancy> getVacancies(List<Integer> ids) {
        List<Vacancy> vacancies = new ArrayList<>();
        Collections.reverse(ids);
        for (Integer id : ids) {
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            JsonNode jsonNode;
            try {
                jsonNode = mapper.readTree(new URL(BASE_URL + id));
            } catch (IOException e) {
                notificationBot.sendMessage("Exception : " + Arrays.toString(e.getStackTrace()));
                continue;
            }
            JsonNode vacancy = jsonNode.get("data");
            Vacancy build = Vacancy.builder()
                    .id(vacancy.get("id").asInt(0))
                    .companyName(vacancy.get("company_name").asText("-"))
                    .positionName(vacancy.get("position_name").asText("-"))
                    .positionSalary(vacancy.get("position_salary").asText("ko'rsatilmagan"))
                    .positionDuties(vacancy.get("position_duties").asText("ko'rsatilmagan"))
                    .positionConditions(vacancy.get("position_conditions").asText("ko'rsatilmagan"))
                    .positionRequirements(vacancy.get("position_requirements").asText("ko'rsatilmagan"))
                    .positionRate(vacancy.get("position_rate").asDouble(1.00))
                    .workType(vacancy.at("/additional_info/work_type").asText())
                    .workExperience(vacancy.at("/additional_info/work_exparence").asText())
                    .region(vacancy.at("/region/name_uz_ln").asText("-"))
                    .district(vacancy.at("/district/name_uz_ln").asText("-"))
                    .build();
            JsonNode phones = vacancy.get("phones");
            if (!phones.isNull()) {
                ArrayNode phoneArray = (ArrayNode) phones;
                if (phoneArray.size() == 0) {
                    build.setPhone("-");
                } else {
                    build.setPhone(phoneArray.get(0).asText());
                }
            } else {
                build.setPhone("berilmagan");
            }
            JsonNode emails = vacancy.get("emails");
            if (!emails.isNull()) {
                ArrayNode emailArray = (ArrayNode) emails;
                if (emailArray.size() == 0) {
                    build.setEmail("berilmagan");
                } else {
                    build.setEmail(emailArray.get(0).asText());
                }
            } else {
                build.setEmail("berilmagan");
            }
            LocalDateTime createdAt = LocalDateTime.parse(vacancy.get("created_at").asText(), DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSS'Z'"));
            build.setCreatedAt(createdAt.plusHours(5).format(DateTimeFormatter.ofPattern("HH:mm,  d-MM-yyyy")));
            String positionRequirements = build.getPositionRequirements();
            if (!positionRequirements.equals("ko'rsatilmagan")) {
                build.setPositionRequirements(positionRequirements.substring(positionRequirements.lastIndexOf(":") + 1));
            }
            vacancies.add(build);
        }
        return vacancies;
    }

    private String message(Vacancy vacancy, String botUsername) {
        String s = """
                #Vakansiya
                <b>%s</b>
                                
                📍<b>Manzil</b> : %s, %s
                🏢<b>Korxona</b> : %s
                💰<b>Maosh</b> :  %s
                📊<b>Stavka</b> :  %.2f
                ⏱ <b>Ish sharoitlari</b> : %s
                🔎<b>Ish turi</b> : %s
                🔰<b>Ish malakasi</b> :  %s
                📆<b>Joylangan sana</b> : %s
                                
                ☎️<b>Telefon raqam</b> : +%s
                ✉️<b>Email</b> :  %s
                                
                %s
                                
                    ➡️ <a href='https://ish.mehnat.uz/vacancies/%d'>Batafsil Saytda ko'ring</a>
                                
                 💼 <a href='https://t.me/%s'>%s bo'sh ish o'rinlari kanali</a>
                """.formatted(
                vacancy.getPositionName(),
                vacancy.getRegion(), vacancy.getDistrict(),
                vacancy.getCompanyName(),
                vacancy.getPositionSalary().equals("ko'rsatilmagan") ? "ko'rsatilmagan" : vacancy.getPositionSalary().substring(0, vacancy.getPositionSalary().lastIndexOf(".")) + " so'm",
                vacancy.getPositionRate(),
                vacancy.getPositionConditions(),
                vacancy.getWorkType().equals("regular") ? "doimiy" : "vaqtinchalik",
                vacancy.getPositionRequirements(),
                vacancy.getCreatedAt(),
                vacancy.getPhone(),
                vacancy.getEmail(),
                "#" + (vacancy.getDistrict().contains(" ") ? vacancy.getDistrict().substring(0, vacancy.getDistrict().lastIndexOf(" ")) : vacancy.getDistrict()).replace("'", "").replace(" ", "") + " #" + (vacancy.getWorkType().equals("regular") ? "doimiy_ish" : "vaqtinchalik_ish"),
                vacancy.getId(),
                channelUsername, vacancy.getRegion()
        );
        return s;
    }


    public int getLastId() {
        return lastId;
    }

    public void setLastId(int lastId) {
        this.lastId = lastId;
    }
}
