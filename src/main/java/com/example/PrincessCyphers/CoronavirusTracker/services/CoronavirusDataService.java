package com.example.PrincessCyphers.CoronavirusTracker.services;

import com.example.PrincessCyphers.CoronavirusTracker.models.AreaStats;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import static java.lang.System.in;

@Service
public class CoronavirusDataService {

    // method to get the data

    public static String Virus_Data_URL = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";

    private List<AreaStats> allStats = new ArrayList<>();           // array of area stats. to drop previous stats and recon with new data.

    public List<AreaStats> getAllStats() {
        return allStats;
    }

    @PostConstruct

    // to create schedule that allow prog to run on set timing, sec, min, day, month, year. setting to check at 6am every day
    @Scheduled(cron = "* * 6 * * *")
    public void fetchViralData() throws IOException, InterruptedException {
        List<AreaStats> newStats = new ArrayList<>();           // allows high user worklaod with no error responses whilst constructing new list-, creates instances of newStats and populates allStats above,
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(Virus_Data_URL))
                .build();
        HttpResponse<String> httpResponse = client.send(request, HttpResponse.BodyHandlers.ofString());

        StringReader csvBodyReader = new StringReader(httpResponse.body());
        Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvBodyReader);
        for (CSVRecord record : records) {
            AreaStats areaStat = new AreaStats();
            areaStat.setState(record.get("Province/State"));
            areaStat.setCountry(record.get("Country/Region"));
            areaStat.setLatestNumberOfCases(Integer.parseInt(record.get(record.size()-1)));         //to access the latest column w/ latest data
            System.out.println(areaStat);
            newStats.add(areaStat);

        }

        this.allStats = newStats;

    }

    }


