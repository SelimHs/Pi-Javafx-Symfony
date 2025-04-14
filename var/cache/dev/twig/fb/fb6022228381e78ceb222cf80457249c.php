<?php

use Twig\Environment;
use Twig\Error\LoaderError;
use Twig\Error\RuntimeError;
use Twig\Extension\CoreExtension;
use Twig\Extension\SandboxExtension;
use Twig\Markup;
use Twig\Sandbox\SecurityError;
use Twig\Sandbox\SecurityNotAllowedTagError;
use Twig\Sandbox\SecurityNotAllowedFilterError;
use Twig\Sandbox\SecurityNotAllowedFunctionError;
use Twig\Source;
use Twig\Template;
use Twig\TemplateWrapper;

/* espace/new.html.twig */
class __TwigTemplate_25d1a6efb5877ada536ef54018ddeefb extends Template
{
    private Source $source;
    /**
     * @var array<string, Template>
     */
    private array $macros = [];

    public function __construct(Environment $env)
    {
        parent::__construct($env);

        $this->source = $this->getSourceContext();

        $this->blocks = [
            'title' => [$this, 'block_title'],
            'body' => [$this, 'block_body'],
        ];
    }

    protected function doGetParent(array $context): bool|string|Template|TemplateWrapper
    {
        // line 1
        return "base.html.twig";
    }

    protected function doDisplay(array $context, array $blocks = []): iterable
    {
        $macros = $this->macros;
        $__internal_5a27a8ba21ca79b61932376b2fa922d2 = $this->extensions["Symfony\\Bundle\\WebProfilerBundle\\Twig\\WebProfilerExtension"];
        $__internal_5a27a8ba21ca79b61932376b2fa922d2->enter($__internal_5a27a8ba21ca79b61932376b2fa922d2_prof = new \Twig\Profiler\Profile($this->getTemplateName(), "template", "espace/new.html.twig"));

        $__internal_6f47bbe9983af81f1e7450e9a3e3768f = $this->extensions["Symfony\\Bridge\\Twig\\Extension\\ProfilerExtension"];
        $__internal_6f47bbe9983af81f1e7450e9a3e3768f->enter($__internal_6f47bbe9983af81f1e7450e9a3e3768f_prof = new \Twig\Profiler\Profile($this->getTemplateName(), "template", "espace/new.html.twig"));

        $this->parent = $this->loadTemplate("base.html.twig", "espace/new.html.twig", 1);
        yield from $this->parent->unwrap()->yield($context, array_merge($this->blocks, $blocks));
        
        $__internal_5a27a8ba21ca79b61932376b2fa922d2->leave($__internal_5a27a8ba21ca79b61932376b2fa922d2_prof);

        
        $__internal_6f47bbe9983af81f1e7450e9a3e3768f->leave($__internal_6f47bbe9983af81f1e7450e9a3e3768f_prof);

    }

    // line 3
    /**
     * @return iterable<null|scalar|\Stringable>
     */
    public function block_title(array $context, array $blocks = []): iterable
    {
        $macros = $this->macros;
        $__internal_5a27a8ba21ca79b61932376b2fa922d2 = $this->extensions["Symfony\\Bundle\\WebProfilerBundle\\Twig\\WebProfilerExtension"];
        $__internal_5a27a8ba21ca79b61932376b2fa922d2->enter($__internal_5a27a8ba21ca79b61932376b2fa922d2_prof = new \Twig\Profiler\Profile($this->getTemplateName(), "block", "title"));

        $__internal_6f47bbe9983af81f1e7450e9a3e3768f = $this->extensions["Symfony\\Bridge\\Twig\\Extension\\ProfilerExtension"];
        $__internal_6f47bbe9983af81f1e7450e9a3e3768f->enter($__internal_6f47bbe9983af81f1e7450e9a3e3768f_prof = new \Twig\Profiler\Profile($this->getTemplateName(), "block", "title"));

        yield "Create Espace";
        
        $__internal_6f47bbe9983af81f1e7450e9a3e3768f->leave($__internal_6f47bbe9983af81f1e7450e9a3e3768f_prof);

        
        $__internal_5a27a8ba21ca79b61932376b2fa922d2->leave($__internal_5a27a8ba21ca79b61932376b2fa922d2_prof);

        yield from [];
    }

    // line 5
    /**
     * @return iterable<null|scalar|\Stringable>
     */
    public function block_body(array $context, array $blocks = []): iterable
    {
        $macros = $this->macros;
        $__internal_5a27a8ba21ca79b61932376b2fa922d2 = $this->extensions["Symfony\\Bundle\\WebProfilerBundle\\Twig\\WebProfilerExtension"];
        $__internal_5a27a8ba21ca79b61932376b2fa922d2->enter($__internal_5a27a8ba21ca79b61932376b2fa922d2_prof = new \Twig\Profiler\Profile($this->getTemplateName(), "block", "body"));

        $__internal_6f47bbe9983af81f1e7450e9a3e3768f = $this->extensions["Symfony\\Bridge\\Twig\\Extension\\ProfilerExtension"];
        $__internal_6f47bbe9983af81f1e7450e9a3e3768f->enter($__internal_6f47bbe9983af81f1e7450e9a3e3768f_prof = new \Twig\Profiler\Profile($this->getTemplateName(), "block", "body"));

        // line 6
        yield "<div class=\"container mt-5\">
  <div class=\"row justify-content-center\">
    <div class=\"col-lg-8\">
      <div class=\"card shadow-lg border-0 rounded-4\">
        <div class=\"card-body p-5\">
          <h2 class=\"mb-4 text-center\">Create a New Espace</h2>

          ";
        // line 13
        yield         $this->env->getRuntime('Symfony\Component\Form\FormRenderer')->renderBlock((isset($context["form"]) || array_key_exists("form", $context) ? $context["form"] : (function () { throw new RuntimeError('Variable "form" does not exist.', 13, $this->source); })()), 'form_start', ["attr" => ["novalidate" => "novalidate"]]);
        yield "

          ";
        // line 16
        yield "          <div class=\"mb-3\">
            ";
        // line 17
        yield $this->env->getRuntime('Symfony\Component\Form\FormRenderer')->searchAndRenderBlock(CoreExtension::getAttribute($this->env, $this->source, (isset($context["form"]) || array_key_exists("form", $context) ? $context["form"] : (function () { throw new RuntimeError('Variable "form" does not exist.', 17, $this->source); })()), "nomEspace", [], "any", false, false, false, 17), 'label', ["label_attr" => ["class" => "form-label"]]);
        yield "
            ";
        // line 18
        yield $this->env->getRuntime('Symfony\Component\Form\FormRenderer')->searchAndRenderBlock(CoreExtension::getAttribute($this->env, $this->source, (isset($context["form"]) || array_key_exists("form", $context) ? $context["form"] : (function () { throw new RuntimeError('Variable "form" does not exist.', 18, $this->source); })()), "nomEspace", [], "any", false, false, false, 18), 'widget', ["attr" => ["class" => "form-control", "required" => "required", "minlength" => "4"]]);
        // line 24
        yield "
            <div class=\"text-danger mt-1\" style=\"font-size: 0.95rem;\">
              ";
        // line 26
        yield $this->env->getRuntime('Symfony\Component\Form\FormRenderer')->searchAndRenderBlock(CoreExtension::getAttribute($this->env, $this->source, (isset($context["form"]) || array_key_exists("form", $context) ? $context["form"] : (function () { throw new RuntimeError('Variable "form" does not exist.', 26, $this->source); })()), "nomEspace", [], "any", false, false, false, 26), 'errors');
        yield "
            </div>
          </div>

          ";
        // line 31
        yield "          <div class=\"mb-3\">
            ";
        // line 32
        yield $this->env->getRuntime('Symfony\Component\Form\FormRenderer')->searchAndRenderBlock(CoreExtension::getAttribute($this->env, $this->source, (isset($context["form"]) || array_key_exists("form", $context) ? $context["form"] : (function () { throw new RuntimeError('Variable "form" does not exist.', 32, $this->source); })()), "adresse", [], "any", false, false, false, 32), 'label');
        yield "
            ";
        // line 33
        yield $this->env->getRuntime('Symfony\Component\Form\FormRenderer')->searchAndRenderBlock(CoreExtension::getAttribute($this->env, $this->source, (isset($context["form"]) || array_key_exists("form", $context) ? $context["form"] : (function () { throw new RuntimeError('Variable "form" does not exist.', 33, $this->source); })()), "adresse", [], "any", false, false, false, 33), 'widget', ["attr" => ["class" => "form-control", "required" => "required", "minlength" => "4"]]);
        // line 39
        yield "
            <div class=\"text-danger mt-1\" style=\"font-size: 0.95rem;\">
              ";
        // line 41
        yield $this->env->getRuntime('Symfony\Component\Form\FormRenderer')->searchAndRenderBlock(CoreExtension::getAttribute($this->env, $this->source, (isset($context["form"]) || array_key_exists("form", $context) ? $context["form"] : (function () { throw new RuntimeError('Variable "form" does not exist.', 41, $this->source); })()), "adresse", [], "any", false, false, false, 41), 'errors');
        yield "
            </div>
          </div>

          ";
        // line 46
        yield "          <div class=\"mb-3\">
            ";
        // line 47
        yield $this->env->getRuntime('Symfony\Component\Form\FormRenderer')->searchAndRenderBlock(CoreExtension::getAttribute($this->env, $this->source, (isset($context["form"]) || array_key_exists("form", $context) ? $context["form"] : (function () { throw new RuntimeError('Variable "form" does not exist.', 47, $this->source); })()), "capacite", [], "any", false, false, false, 47), 'label');
        yield "
            ";
        // line 48
        yield $this->env->getRuntime('Symfony\Component\Form\FormRenderer')->searchAndRenderBlock(CoreExtension::getAttribute($this->env, $this->source, (isset($context["form"]) || array_key_exists("form", $context) ? $context["form"] : (function () { throw new RuntimeError('Variable "form" does not exist.', 48, $this->source); })()), "capacite", [], "any", false, false, false, 48), 'widget', ["attr" => ["class" => "form-control", "required" => "required", "type" => "number", "min" => "50"]]);
        // line 55
        yield "
            <div class=\"text-danger mt-1\" style=\"font-size: 0.95rem;\">
              ";
        // line 57
        yield $this->env->getRuntime('Symfony\Component\Form\FormRenderer')->searchAndRenderBlock(CoreExtension::getAttribute($this->env, $this->source, (isset($context["form"]) || array_key_exists("form", $context) ? $context["form"] : (function () { throw new RuntimeError('Variable "form" does not exist.', 57, $this->source); })()), "capacite", [], "any", false, false, false, 57), 'errors');
        yield "
            </div>
          </div>

          ";
        // line 62
        yield "          <div class=\"mb-3\">
            ";
        // line 63
        yield $this->env->getRuntime('Symfony\Component\Form\FormRenderer')->searchAndRenderBlock(CoreExtension::getAttribute($this->env, $this->source, (isset($context["form"]) || array_key_exists("form", $context) ? $context["form"] : (function () { throw new RuntimeError('Variable "form" does not exist.', 63, $this->source); })()), "prix", [], "any", false, false, false, 63), 'label');
        yield "
            ";
        // line 64
        yield $this->env->getRuntime('Symfony\Component\Form\FormRenderer')->searchAndRenderBlock(CoreExtension::getAttribute($this->env, $this->source, (isset($context["form"]) || array_key_exists("form", $context) ? $context["form"] : (function () { throw new RuntimeError('Variable "form" does not exist.', 64, $this->source); })()), "prix", [], "any", false, false, false, 64), 'widget', ["attr" => ["class" => "form-control", "required" => "required", "type" => "number"]]);
        // line 70
        yield "
            <div class=\"text-danger mt-1\" style=\"font-size: 0.95rem;\">
              ";
        // line 72
        yield $this->env->getRuntime('Symfony\Component\Form\FormRenderer')->searchAndRenderBlock(CoreExtension::getAttribute($this->env, $this->source, (isset($context["form"]) || array_key_exists("form", $context) ? $context["form"] : (function () { throw new RuntimeError('Variable "form" does not exist.', 72, $this->source); })()), "prix", [], "any", false, false, false, 72), 'errors');
        yield "
            </div>
          </div>

          ";
        // line 77
        yield "          <div class=\"mb-3\">
            ";
        // line 78
        yield $this->env->getRuntime('Symfony\Component\Form\FormRenderer')->searchAndRenderBlock(CoreExtension::getAttribute($this->env, $this->source, (isset($context["form"]) || array_key_exists("form", $context) ? $context["form"] : (function () { throw new RuntimeError('Variable "form" does not exist.', 78, $this->source); })()), "Type_espace", [], "any", false, false, false, 78), 'label');
        yield "
            ";
        // line 79
        yield $this->env->getRuntime('Symfony\Component\Form\FormRenderer')->searchAndRenderBlock(CoreExtension::getAttribute($this->env, $this->source, (isset($context["form"]) || array_key_exists("form", $context) ? $context["form"] : (function () { throw new RuntimeError('Variable "form" does not exist.', 79, $this->source); })()), "Type_espace", [], "any", false, false, false, 79), 'widget', ["attr" => ["class" => "form-control", "required" => "required"]]);
        // line 84
        yield "
            <div class=\"text-danger mt-1\" style=\"font-size: 0.95rem;\">
              ";
        // line 86
        yield $this->env->getRuntime('Symfony\Component\Form\FormRenderer')->searchAndRenderBlock(CoreExtension::getAttribute($this->env, $this->source, (isset($context["form"]) || array_key_exists("form", $context) ? $context["form"] : (function () { throw new RuntimeError('Variable "form" does not exist.', 86, $this->source); })()), "Type_espace", [], "any", false, false, false, 86), 'errors');
        yield "
            </div>
          </div>

          ";
        // line 91
        yield "          <div class=\"mb-3\">
            ";
        // line 92
        yield $this->env->getRuntime('Symfony\Component\Form\FormRenderer')->searchAndRenderBlock(CoreExtension::getAttribute($this->env, $this->source, (isset($context["form"]) || array_key_exists("form", $context) ? $context["form"] : (function () { throw new RuntimeError('Variable "form" does not exist.', 92, $this->source); })()), "disponibilite", [], "any", false, false, false, 92), 'label');
        yield "
            ";
        // line 93
        yield $this->env->getRuntime('Symfony\Component\Form\FormRenderer')->searchAndRenderBlock(CoreExtension::getAttribute($this->env, $this->source, (isset($context["form"]) || array_key_exists("form", $context) ? $context["form"] : (function () { throw new RuntimeError('Variable "form" does not exist.', 93, $this->source); })()), "disponibilite", [], "any", false, false, false, 93), 'widget', ["attr" => ["class" => "form-control", "readonly" => "readonly"], "value" => "Disponible"]);
        // line 99
        yield "
            <div class=\"text-danger mt-1\" style=\"font-size: 0.95rem;\">
              ";
        // line 101
        yield $this->env->getRuntime('Symfony\Component\Form\FormRenderer')->searchAndRenderBlock(CoreExtension::getAttribute($this->env, $this->source, (isset($context["form"]) || array_key_exists("form", $context) ? $context["form"] : (function () { throw new RuntimeError('Variable "form" does not exist.', 101, $this->source); })()), "disponibilite", [], "any", false, false, false, 101), 'errors');
        yield "
            </div>
          </div>

          ";
        // line 106
        yield "          <div class=\"mb-3\">
            ";
        // line 107
        yield $this->env->getRuntime('Symfony\Component\Form\FormRenderer')->searchAndRenderBlock(CoreExtension::getAttribute($this->env, $this->source, (isset($context["form"]) || array_key_exists("form", $context) ? $context["form"] : (function () { throw new RuntimeError('Variable "form" does not exist.', 107, $this->source); })()), "image", [], "any", false, false, false, 107), 'label');
        yield "
            ";
        // line 108
        yield $this->env->getRuntime('Symfony\Component\Form\FormRenderer')->searchAndRenderBlock(CoreExtension::getAttribute($this->env, $this->source, (isset($context["form"]) || array_key_exists("form", $context) ? $context["form"] : (function () { throw new RuntimeError('Variable "form" does not exist.', 108, $this->source); })()), "image", [], "any", false, false, false, 108), 'widget', ["attr" => ["class" => "form-control", "required" => "required"]]);
        // line 113
        yield "
            <div class=\"text-danger mt-1\" style=\"font-size: 0.95rem;\">
              ";
        // line 115
        yield $this->env->getRuntime('Symfony\Component\Form\FormRenderer')->searchAndRenderBlock(CoreExtension::getAttribute($this->env, $this->source, (isset($context["form"]) || array_key_exists("form", $context) ? $context["form"] : (function () { throw new RuntimeError('Variable "form" does not exist.', 115, $this->source); })()), "image", [], "any", false, false, false, 115), 'errors');
        yield "
            </div>
          </div>

          ";
        // line 119
        yield $this->env->getRuntime('Symfony\Component\Form\FormRenderer')->searchAndRenderBlock(CoreExtension::getAttribute($this->env, $this->source, (isset($context["form"]) || array_key_exists("form", $context) ? $context["form"] : (function () { throw new RuntimeError('Variable "form" does not exist.', 119, $this->source); })()), "user", [], "any", false, false, false, 119), 'widget', ["attr" => ["style" => "display: none"]]);
        yield "

          <div class=\"text-center\">
            <button class=\"btn btn-primary px-4\">";
        // line 122
        yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape(((array_key_exists("button_label", $context)) ? (Twig\Extension\CoreExtension::default((isset($context["button_label"]) || array_key_exists("button_label", $context) ? $context["button_label"] : (function () { throw new RuntimeError('Variable "button_label" does not exist.', 122, $this->source); })()), "Save")) : ("Save")), "html", null, true);
        yield "</button>
          </div>

          ";
        // line 125
        yield         $this->env->getRuntime('Symfony\Component\Form\FormRenderer')->renderBlock((isset($context["form"]) || array_key_exists("form", $context) ? $context["form"] : (function () { throw new RuntimeError('Variable "form" does not exist.', 125, $this->source); })()), 'form_end');
        yield "
        </div>
      </div>
    </div>
  </div>
</div>

<div class=\"text-center mt-3\">
  <a href=\"";
        // line 133
        yield $this->extensions['Symfony\Bridge\Twig\Extension\RoutingExtension']->getPath("app_espace_index");
        yield "\" class=\"btn btn-link\">← Back to list</a>
</div>
";
        
        $__internal_6f47bbe9983af81f1e7450e9a3e3768f->leave($__internal_6f47bbe9983af81f1e7450e9a3e3768f_prof);

        
        $__internal_5a27a8ba21ca79b61932376b2fa922d2->leave($__internal_5a27a8ba21ca79b61932376b2fa922d2_prof);

        yield from [];
    }

    /**
     * @codeCoverageIgnore
     */
    public function getTemplateName(): string
    {
        return "espace/new.html.twig";
    }

    /**
     * @codeCoverageIgnore
     */
    public function isTraitable(): bool
    {
        return false;
    }

    /**
     * @codeCoverageIgnore
     */
    public function getDebugInfo(): array
    {
        return array (  277 => 133,  266 => 125,  260 => 122,  254 => 119,  247 => 115,  243 => 113,  241 => 108,  237 => 107,  234 => 106,  227 => 101,  223 => 99,  221 => 93,  217 => 92,  214 => 91,  207 => 86,  203 => 84,  201 => 79,  197 => 78,  194 => 77,  187 => 72,  183 => 70,  181 => 64,  177 => 63,  174 => 62,  167 => 57,  163 => 55,  161 => 48,  157 => 47,  154 => 46,  147 => 41,  143 => 39,  141 => 33,  137 => 32,  134 => 31,  127 => 26,  123 => 24,  121 => 18,  117 => 17,  114 => 16,  109 => 13,  100 => 6,  87 => 5,  64 => 3,  41 => 1,);
    }

    public function getSourceContext(): Source
    {
        return new Source("{% extends 'base.html.twig' %}

{% block title %}Create Espace{% endblock %}

{% block body %}
<div class=\"container mt-5\">
  <div class=\"row justify-content-center\">
    <div class=\"col-lg-8\">
      <div class=\"card shadow-lg border-0 rounded-4\">
        <div class=\"card-body p-5\">
          <h2 class=\"mb-4 text-center\">Create a New Espace</h2>

          {{ form_start(form, {'attr': {'novalidate': 'novalidate'}}) }}

          {# Champ nomEspace : obligatoire, minimum 4 caractères #}
          <div class=\"mb-3\">
            {{ form_label(form.nomEspace, null, {'label_attr': {'class': 'form-label'}}) }}
            {{ form_widget(form.nomEspace, {
              'attr': {
                'class': 'form-control',
                'required': 'required',
                'minlength': '4'
              }
            }) }}
            <div class=\"text-danger mt-1\" style=\"font-size: 0.95rem;\">
              {{ form_errors(form.nomEspace) }}
            </div>
          </div>

          {# Champ adresse : obligatoire, minimum 4 caractères #}
          <div class=\"mb-3\">
            {{ form_label(form.adresse) }}
            {{ form_widget(form.adresse, {
              'attr': {
                'class': 'form-control',
                'required': 'required',
                'minlength': '4'
              }
            }) }}
            <div class=\"text-danger mt-1\" style=\"font-size: 0.95rem;\">
              {{ form_errors(form.adresse) }}
            </div>
          </div>

          {# Champ capacité : obligatoire, type number et valeur minimale 50 #}
          <div class=\"mb-3\">
            {{ form_label(form.capacite) }}
            {{ form_widget(form.capacite, {
              'attr': {
                'class': 'form-control',
                'required': 'required',
                'type': 'number',
                'min': '50'
              }
            }) }}
            <div class=\"text-danger mt-1\" style=\"font-size: 0.95rem;\">
              {{ form_errors(form.capacite) }}
            </div>
          </div>

          {# Champ prix : obligatoire et de type numérique #}
          <div class=\"mb-3\">
            {{ form_label(form.prix) }}
            {{ form_widget(form.prix, {
              'attr': {
                'class': 'form-control',
                'required': 'required',
                'type': 'number'
              }
            }) }}
            <div class=\"text-danger mt-1\" style=\"font-size: 0.95rem;\">
              {{ form_errors(form.prix) }}
            </div>
          </div>

          {# Champ Type_espace : liste déroulante, obligatoire #}
          <div class=\"mb-3\">
            {{ form_label(form.Type_espace) }}
            {{ form_widget(form.Type_espace, {
              'attr': {
                'class': 'form-control',
                'required': 'required'
              }
            }) }}
            <div class=\"text-danger mt-1\" style=\"font-size: 0.95rem;\">
              {{ form_errors(form.Type_espace) }}
            </div>
          </div>

          {# Champ disponibilite : affiché en lecture seule avec valeur par défaut \"Disponible\" #}
          <div class=\"mb-3\">
            {{ form_label(form.disponibilite) }}
            {{ form_widget(form.disponibilite, {
              'attr': {
                'class': 'form-control',
                'readonly': 'readonly'
              },
              'value': 'Disponible'
            }) }}
            <div class=\"text-danger mt-1\" style=\"font-size: 0.95rem;\">
              {{ form_errors(form.disponibilite) }}
            </div>
          </div>

          {# Champ image : obligatoire #}
          <div class=\"mb-3\">
            {{ form_label(form.image) }}
            {{ form_widget(form.image, {
              'attr': {
                'class': 'form-control',
                'required': 'required'
              }
            }) }}
            <div class=\"text-danger mt-1\" style=\"font-size: 0.95rem;\">
              {{ form_errors(form.image) }}
            </div>
          </div>

          {{ form_widget(form.user, {'attr': {'style': 'display: none'}}) }}

          <div class=\"text-center\">
            <button class=\"btn btn-primary px-4\">{{ button_label|default('Save') }}</button>
          </div>

          {{ form_end(form) }}
        </div>
      </div>
    </div>
  </div>
</div>

<div class=\"text-center mt-3\">
  <a href=\"{{ path('app_espace_index') }}\" class=\"btn btn-link\">← Back to list</a>
</div>
{% endblock %}
", "espace/new.html.twig", "C:\\wamp64\\gestion_espace_symfony\\templates\\espace\\new.html.twig");
    }
}
