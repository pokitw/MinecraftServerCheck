package com.minecraft.pingchecker;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.IOException;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText serverAddressInput;
    private Button checkPingButton;
    private ProgressBar loadingProgress;
    private TextView pingResultText;
    private TextView tipsText;
    private TextView livePingResultText;

    private List<Integer> pingHistory;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        serverAddressInput = findViewById(R.id.server_address_input);
        checkPingButton = findViewById(R.id.check_ping_button);
        loadingProgress = findViewById(R.id.loading_progress);
        pingResultText = findViewById(R.id.ping_result_text);
        tipsText = findViewById(R.id.tips_text);
        livePingResultText = findViewById(R.id.live_ping_result_text);

        pingHistory = new ArrayList<>();
        handler = new Handler();

        checkPingButton.setOnClickListener(v -> {
            String serverAddress = serverAddressInput.getText().toString().trim();

            if (!serverAddress.isEmpty()) {
                new PingTask().execute(serverAddress);
            }
        });
    }

    private class PingTask extends AsyncTask<String, Void, Integer> {

        @Override
        protected void onPreExecute() {
            loadingProgress.setVisibility(View.VISIBLE);
        }

        @Override
        protected Integer doInBackground(String... params) {
            return checkPing(params[0]);
        }

        @Override
        protected void onPostExecute(Integer ping) {
            loadingProgress.setVisibility(View.GONE);

            if (ping != -1) {
                pingResultText.setText("Your ping to the server is " + ping + " ms.");

                pingHistory.add(ping);
                updateAveragePing();

                tipsText.setText(generateImprovementTips(ping));
            } else {
                pingResultText.setText("The server is offline or the address is incorrect.");

                String similarServer = findSimilarServer(serverAddressInput.getText().toString().trim());
                if (similarServer != null) {
                    tipsText.setText("Did you mean: " + similarServer + "?");
                }
            }

            updateLivePing();
        }
    }

    private int checkPing(String serverAddress) {
        try {
            InetAddress server = InetAddress.getByName(serverAddress);
            long startTime = System.currentTimeMillis();
            if (server.isReachable(5000)) {
                long endTime = System.currentTimeMillis();
                return (int) (endTime - startTime);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return -1;
    }

    private void updateAveragePing() {
        int numberOfChecks = pingHistory.size();
        if (numberOfChecks > 0) {
            int totalPing = 0;
            for (int ping : pingHistory) {
                totalPing += ping;
            }
            double averagePing = (double) totalPing / numberOfChecks;
            pingResultText.append(" The average ping over " + numberOfChecks + " checks is " + String.format("%.2f", averagePing) + " ms.");
        }
    }

    private String generateImprovementTips(int ping) {
        if (ping < 100) {
            return "Your ping is excellent! No need for improvement.";
        } else if (ping < 200) {
            return "Your ping is good. To improve it further, consider using a wired internet connection or closing bandwidth-intensive applications.";
        } else {
            return "Your ping is high. To improve it, try using a wired internet connection, closing bandwidth-intensive applications, or using a VPN to connect to a server closer to the game server's location.";
        }
    }

    private String findSimilarServer(String serverAddress) {
        // Logic to find similar servers here
        // Return the address of a similar server, or null if none found
        return null;
    }

    private void updateLivePing() {
        handler.removeCallbacksAndMessages(null);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                String serverAddress = serverAddressInput.getText().toString().trim();
                if (!serverAddress.isEmpty()) {
                    new LivePingTask().execute(serverAddress);
                }
                updateLivePing();
            }
        }, 5000);
    }

    private class LivePingTask extends AsyncTask<String, Void, Integer> {

        @Override
        protected Integer doInBackground(String... params) {
            return checkPing(params[0]);
        }

        @Override
        protected void onPostExecute(Integer ping) {
            if (ping != -1) {
                livePingResultText.setText("Live ping: " + ping + " ms");
            } else {
                livePingResultText.setText("Live ping: N/A");
            }
        }
    }
}
