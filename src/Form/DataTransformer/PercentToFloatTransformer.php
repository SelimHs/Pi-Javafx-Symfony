<?php
namespace App\Form\DataTransformer;

use Symfony\Component\Form\DataTransformerInterface;

class PercentToFloatTransformer implements DataTransformerInterface
{
    public function transform($value): mixed
    {
        // Value is float (e.g. 20.0), return as "20%"
        return $value !== null ? rtrim(rtrim((string)$value, '0'), '.') . '%' : '';
    }

    public function reverseTransform($value): mixed
    {
        if ($value === null) {
            return null;
        }

        // Remove % if present
        $value = str_replace('%', '', trim($value));

        if (!is_numeric($value)) {
            throw new \UnexpectedValueException('Le pourcentage doit être un nombre.');
        }

        return (float) $value;
    }
}
