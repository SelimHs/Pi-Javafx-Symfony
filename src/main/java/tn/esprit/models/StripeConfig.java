package tn.esprit.models;
import com.stripe.Stripe;

public class StripeConfig {
    private static final String SECRET_KEY = "sk_test_51Qw1SOHIrxITnx0jAmWBThATBb4RYLgGM3MjeFg4pRKjEEyN8SFPEU9CwOhNWvHOI41CPeyI0VU5DRoLFpcvlmjk00N5IxsvEu";  // Remplace par ta clé secrète

    public static void init() {
        Stripe.apiKey = SECRET_KEY;
    }
}
