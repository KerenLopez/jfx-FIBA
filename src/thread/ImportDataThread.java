package thread;

import java.io.BufferedReader;
import java.io.IOException;

import exceptions.NegativeValueException;
import javafx.application.Platform;
import model.Fiba;

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
				
							//addPlayer(parts[1],parts[2],parts[0],parts[3],parts[5],parts[6],parts[7],parts[7]);
							
							
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