package api;

import model.SMS;
import java.time.LocalDateTime;

public class VivoAdapter implements SMSSender {

	private VivoService vivoService;

    public VivoAdapter(VivoService vivoService) {
        if (vivoService != null) {
            this.vivoService = vivoService;
        } else {
            throw new IllegalArgumentException("VivoService não pode ser nulo");
        }
    }
    
    

    @Override
    public boolean sendSMS(SMS sms) {
        String origem = sms.getOrigem();
        String destino = sms.getDestino();
        LocalDateTime time = sms.getTimestamp();
        String texto = sms.getTexto();
        String[] msgs = new String[(int) Math.ceilDiv(texto.length(), 18)];
        for(int i = 0; i< msgs.length; i++) {
        	if (i == msgs.length -1) {
        		msgs[i] = texto.substring(i*18, texto.length());
        	} else {
        		msgs[i] = texto.substring(i*18, (i+1)*18);
        	}
        }

        try {
            vivoService.enviarSMS(origem, destino, time, msgs);
            return true;
        } catch (SMSException e) {
            e.printStackTrace();
            return false;
        }
    }
}
