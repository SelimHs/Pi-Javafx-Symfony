package tn.esprit.services;

import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import java.util.HashMap;
import java.util.Map;

public class StripeService {

    public static PaymentIntent createPayment(double amount, String currency) {
        try {
            Map<String, Object> params = new HashMap<>();
            params.put("amount", (int) (amount * 100)); // Stripe requires the amount in cents
            params.put("currency", currency);
            params.put("payment_method_types", new String[]{"card"}); // Card payment

            return PaymentIntent.create(params);
        } catch (StripeException e) {
            e.printStackTrace();
            return null;
        }
    }
}
