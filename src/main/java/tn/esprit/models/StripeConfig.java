package tn.esprit.models;
import com.stripe.Stripe;

public class StripeConfig {
    private static final String SECRET_KEY = "sk_test_51QzP2uPFHNYg7RkVtRbawzVoW57klCf9OyWZ7r9zzUV7X9F8exNoDMFxUyiWqpVFr7jvGapsKGRsuBGFtLSvM34l00kPHtYFrm";  // Remplace par ta clé secrète

    public static void init() {
        Stripe.apiKey = SECRET_KEY;
    }
}
