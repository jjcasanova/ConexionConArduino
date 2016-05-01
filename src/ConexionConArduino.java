import java.util.List;

import com.panamahitek.*;

import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;
public class ConexionConArduino {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PanamaHitek_Arduino miArduino= new PanamaHitek_Arduino();
		PanamaHitek_multiMessage multimensaje= new PanamaHitek_multiMessage(5, miArduino);
		try {
			miArduino.arduinoRX("COM3", 9600, new SerialPortEventListener() {
				List<String> listadoValores;
				@Override
				public void serialEvent(SerialPortEvent arg0) {
					// TODO Auto-generated method stub
					if (multimensaje.DataReceptionCompleted()){
						listadoValores=multimensaje.getMessageList();
						System.out.println("Temperatura agua " + listadoValores.get(0));
						System.out.println("Temperatura aire " + listadoValores.get(1));
						System.out.println("Presion Atmosferica " + listadoValores.get(2));
						System.out.println("Humedad Relativa " + listadoValores.get(3));
						multimensaje.flushBuffer();
					}
				}
			});
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
