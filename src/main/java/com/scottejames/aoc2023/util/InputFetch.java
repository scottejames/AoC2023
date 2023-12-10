package com.scottejames.aoc2023.util;

import com.google.common.net.HttpHeaders;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class InputFetch {

    private String _sessionToken;
    private final Path  _puzzleCacheDir = Path.of("puzzleData");
    private final OkHttpClient _httpClient = new OkHttpClient();

    public static void main(String [] args)  {
        InputFetch i = new InputFetch();
        String data = i.fetchPuzzleInput(1);
        System.out.println(data);
    }
    public String fetchPuzzleInput(int day) {
        String data = null;
        try {
            data =  fetchPuzzleInputLocally(day);
            System.out.println("Retrieved data from local store");
        } catch (IOException e){
            System.err.printf("Could not get data for day %s locally", day);
        }
        if (data == null) {
            try {
                data = fetchPuzzleInputRemote(day);
                System.out.println("Retrieved data from remote store");
                storePuzzleInputLocally(day, data);
            } catch (IOException e) {
                System.err.println("Could not get (or store!) data for day remotely");
            }
        }
        return data;
    }
    public void storePuzzleInputLocally(int day, String input) throws IOException {
        Files.createDirectories(_puzzleCacheDir);
        var cache = _puzzleCacheDir.resolve(String.valueOf(day));
        Files.writeString(cache,input);
    }
    public String fetchPuzzleInputLocally(int day) throws IOException {
        return Files.readString(_puzzleCacheDir.resolve(String.valueOf(day)));

    }
    public String fetchPuzzleInputRemote(int day) throws IOException {

        var request = new Request.Builder()
                .url(getRemotePuzzleInputUrl(day))
                .header(HttpHeaders.COOKIE, "session=" + getSessionToken())
                .get()
                .build();


        try (var response = _httpClient.newCall(request).execute()) {
            if (response.code() != 200) {
                throw new IOException("Request was not successful. Status code = " + response.code());
            }
            var body = response.body();
            if (body == null) {
                throw new IOException("Request body was empty");
            }
            return body.string();
        }
    }

    HttpUrl getRemotePuzzleInputUrl(int day) {
        System.err.printf("Getting info for %s ", day);
        return HttpUrl.get("https://adventofcode.com/2023/day/" + day + "/input");
    }
    synchronized String  getSessionToken() throws IOException {
        try {
            if (_sessionToken == null) {
                _sessionToken = Files.readString(Path.of("src/main/cookie-google.txt")).trim();
            }
            return _sessionToken;
        } catch (IOException e) {
            throw new IOException("Couldn't get session data from cookie.txt", e);
        }
    }
}
