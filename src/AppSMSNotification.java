import api.SMSSender;
import api.TimService;
import api.VivoAdapter;
import api.VivoService;
import model.SMS;

public class AppSMSNotification {

    public static void main(String[] args) {
        SMSSender timService = new TimService();
        SMSSender vivoAdapter = new VivoAdapter(new VivoService());

        SMS message = new SMS("83988885544", "83988221133", "Bom dia. Seu boleto já está disponível para pagamento");

        timService.sendSMS(message);

        vivoAdapter.sendSMS(message);
    }
}
