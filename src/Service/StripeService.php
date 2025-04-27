<?php

namespace App\Service;

use Stripe\Stripe;
use Stripe\PaymentIntent;

class StripeService
{
    public function __construct()
    {
        // 🔥 API KEY DIRECTEMENT ICI
        Stripe::setApiKey('sk_test_51QzP2uPFHNYg7RkVtRbawzVoW57klCf9OyWZ7r9zzUV7X9F8exNoDMFxUyiWqpVFr7jvGapsKGRsuBGFtLSvM34l00kPHtYFrm');
    }

    public function createPaymentIntent(float $amount, string $currency = 'usd'): ?PaymentIntent
    {
        try {
            return PaymentIntent::create([
                'amount' => (int)($amount * 100), // Stripe attend des CENTS
                'currency' => $currency,
                'payment_method_types' => ['card'],
            ]);
        } catch (\Exception $e) {
            return null;
        }
    }
}
