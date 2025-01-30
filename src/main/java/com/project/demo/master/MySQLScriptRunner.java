package com.project.demo.master;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class MySQLScriptRunner {
    public static void main(String[] args) {
        try {
            // Construire la commande MySQL avec l'hôte et l'utilisateur
            ProcessBuilder processBuilder = new ProcessBuilder(
                    "mysql", "-u", "replicator", "-h", "192.168.63.158", "-proot", "-e", "STOP REPLICA; CHANGE MASTER TO MASTER_HOST='192.168.63.103', MASTER_USER='replicator', MASTER_PASSWORD='root', MASTER_LOG_FILE='LAPTOP-H3FGBG6J-bin.000061', MASTER_LOG_POS=445; START REPLICA;"
            );

            // Exécuter le processus
            Process process = processBuilder.start();

            // Lire la sortie du processus
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            // Lire les erreurs
            BufferedReader errorReader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
            while ((line = errorReader.readLine()) != null) {
                System.err.println(line);
            }

            // Attendre la fin du processus
            int exitCode = process.waitFor();
            System.out.println("Process terminé avec code : " + exitCode);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
