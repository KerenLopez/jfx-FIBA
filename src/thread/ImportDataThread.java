package thread;

import java.io.BufferedReader;
import java.io.IOException;

import javafx.application.Platform;
import model.Fiba;
import model.Player;

public class ImportDataThread extends Thread{

	private Fiba fiba;
	private BufferedReader br;

	public ImportDataThread(Fiba fiba, BufferedReader br) {
		this.fiba = fiba;
		this.br = br;
	}

	public void run() {

		Platform.runLater(new Thread() {

			@Override
			public void run() {
				String line = "";
				try {
					line = br.readLine();

					while(line != null) {

						try {
							String[] parts = line.split(";");

							//addPlayer(String n, String ag, String t, String p, String bo, String a, String st, String bl)
							if(!parts[0].equals("Tm")) {
								Integer age = Integer.parseInt(parts[2]);
								Double points = Double.parseDouble(parts[3]);
								Double bounces = Double.parseDouble(parts[4]);
								Double assists = Double.parseDouble(parts[5]);
								Double steals = Double.parseDouble(parts[6]);
								Double blocks = Double.parseDouble(parts[7]);

								Player player = new Player(parts[1],age,parts[0], points, bounces, assists, steals, blocks);
								fiba.getABBofPointsByGame().insertNode(player.getPoints(), player);
								fiba.getABBofAssists().insertNode(player.getAssists(), player);
								fiba.getAVLPointsByGame().insert(player.getPoints(), player);
								fiba.getAVLAssists().insert(player.getAssists(), player);
								fiba.getAVLBlocksByGame().insert(player.getBlocks(), player);
								fiba.getRbtSteals().insert(player.getSteals(), player);
								fiba.getPlayersByBounces().add(player);
								fiba.sortPlayers();
							}

							line = br.readLine();

						} catch (NumberFormatException e) {
							line = br.readLine();
						}
					}

				}catch (IOException e) {
				}
				try {
					br.close();
				} catch (IOException e) {
				}
			}
		});
	}
}