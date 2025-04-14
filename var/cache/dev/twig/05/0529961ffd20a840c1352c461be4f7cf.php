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

/* organisateur/new.html.twig */
class __TwigTemplate_b29b6ff3a005d56cccff487d066f3c45 extends Template
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
        $__internal_5a27a8ba21ca79b61932376b2fa922d2->enter($__internal_5a27a8ba21ca79b61932376b2fa922d2_prof = new \Twig\Profiler\Profile($this->getTemplateName(), "template", "organisateur/new.html.twig"));

        $__internal_6f47bbe9983af81f1e7450e9a3e3768f = $this->extensions["Symfony\\Bridge\\Twig\\Extension\\ProfilerExtension"];
        $__internal_6f47bbe9983af81f1e7450e9a3e3768f->enter($__internal_6f47bbe9983af81f1e7450e9a3e3768f_prof = new \Twig\Profiler\Profile($this->getTemplateName(), "template", "organisateur/new.html.twig"));

        $this->parent = $this->loadTemplate("base.html.twig", "organisateur/new.html.twig", 1);
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

        yield "Create Organisateur";
        
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
          <h2 class=\"mb-4 text-center\">Create a New Organisateur</h2>

          ";
        // line 13
        yield         $this->env->getRuntime('Symfony\Component\Form\FormRenderer')->renderBlock((isset($context["form"]) || array_key_exists("form", $context) ? $context["form"] : (function () { throw new RuntimeError('Variable "form" does not exist.', 13, $this->source); })()), 'form_start', ["attr" => ["novalidate" => "novalidate"]]);
        yield "

          ";
        // line 16
        yield "          <div class=\"mb-3\">
            ";
        // line 17
        yield $this->env->getRuntime('Symfony\Component\Form\FormRenderer')->searchAndRenderBlock(CoreExtension::getAttribute($this->env, $this->source, (isset($context["form"]) || array_key_exists("form", $context) ? $context["form"] : (function () { throw new RuntimeError('Variable "form" does not exist.', 17, $this->source); })()), "nom_org", [], "any", false, false, false, 17), 'label', ["label_attr" => ["class" => "form-label"]]);
        yield "
            ";
        // line 18
        yield $this->env->getRuntime('Symfony\Component\Form\FormRenderer')->searchAndRenderBlock(CoreExtension::getAttribute($this->env, $this->source, (isset($context["form"]) || array_key_exists("form", $context) ? $context["form"] : (function () { throw new RuntimeError('Variable "form" does not exist.', 18, $this->source); })()), "nom_org", [], "any", false, false, false, 18), 'widget', ["attr" => ["class" => "form-control", "required" => "required", "minlength" => "3", "placeholder" => "Nom de l'organisateur"]]);
        // line 25
        yield "
            <div class=\"text-danger mt-1\" style=\"font-size: 0.95rem;\">
              ";
        // line 27
        yield $this->env->getRuntime('Symfony\Component\Form\FormRenderer')->searchAndRenderBlock(CoreExtension::getAttribute($this->env, $this->source, (isset($context["form"]) || array_key_exists("form", $context) ? $context["form"] : (function () { throw new RuntimeError('Variable "form" does not exist.', 27, $this->source); })()), "nom_org", [], "any", false, false, false, 27), 'errors');
        yield "
            </div>
          </div>

          ";
        // line 32
        yield "          <div class=\"mb-3\">
            ";
        // line 33
        yield $this->env->getRuntime('Symfony\Component\Form\FormRenderer')->searchAndRenderBlock(CoreExtension::getAttribute($this->env, $this->source, (isset($context["form"]) || array_key_exists("form", $context) ? $context["form"] : (function () { throw new RuntimeError('Variable "form" does not exist.', 33, $this->source); })()), "prenom_org", [], "any", false, false, false, 33), 'label');
        yield "
            ";
        // line 34
        yield $this->env->getRuntime('Symfony\Component\Form\FormRenderer')->searchAndRenderBlock(CoreExtension::getAttribute($this->env, $this->source, (isset($context["form"]) || array_key_exists("form", $context) ? $context["form"] : (function () { throw new RuntimeError('Variable "form" does not exist.', 34, $this->source); })()), "prenom_org", [], "any", false, false, false, 34), 'widget', ["attr" => ["class" => "form-control", "required" => "required", "minlength" => "3", "placeholder" => "Prénom de l'organisateur"]]);
        // line 41
        yield "
            <div class=\"text-danger mt-1\" style=\"font-size: 0.95rem;\">
              ";
        // line 43
        yield $this->env->getRuntime('Symfony\Component\Form\FormRenderer')->searchAndRenderBlock(CoreExtension::getAttribute($this->env, $this->source, (isset($context["form"]) || array_key_exists("form", $context) ? $context["form"] : (function () { throw new RuntimeError('Variable "form" does not exist.', 43, $this->source); })()), "prenom_org", [], "any", false, false, false, 43), 'errors');
        yield "
            </div>
          </div>

          ";
        // line 48
        yield "          <div class=\"mb-3\">
            ";
        // line 49
        yield $this->env->getRuntime('Symfony\Component\Form\FormRenderer')->searchAndRenderBlock(CoreExtension::getAttribute($this->env, $this->source, (isset($context["form"]) || array_key_exists("form", $context) ? $context["form"] : (function () { throw new RuntimeError('Variable "form" does not exist.', 49, $this->source); })()), "description_org", [], "any", false, false, false, 49), 'label');
        yield "
            ";
        // line 50
        yield $this->env->getRuntime('Symfony\Component\Form\FormRenderer')->searchAndRenderBlock(CoreExtension::getAttribute($this->env, $this->source, (isset($context["form"]) || array_key_exists("form", $context) ? $context["form"] : (function () { throw new RuntimeError('Variable "form" does not exist.', 50, $this->source); })()), "description_org", [], "any", false, false, false, 50), 'widget', ["attr" => ["class" => "form-control", "required" => "required", "minlength" => "5", "rows" => "3", "placeholder" => "Décrivez l'organisateur..."]]);
        // line 58
        yield "
            <div class=\"text-danger mt-1\" style=\"font-size: 0.95rem;\">
              ";
        // line 60
        yield $this->env->getRuntime('Symfony\Component\Form\FormRenderer')->searchAndRenderBlock(CoreExtension::getAttribute($this->env, $this->source, (isset($context["form"]) || array_key_exists("form", $context) ? $context["form"] : (function () { throw new RuntimeError('Variable "form" does not exist.', 60, $this->source); })()), "description_org", [], "any", false, false, false, 60), 'errors');
        yield "
            </div>
          </div>

          ";
        // line 65
        yield "          <div class=\"mb-3\">
            ";
        // line 66
        yield $this->env->getRuntime('Symfony\Component\Form\FormRenderer')->searchAndRenderBlock(CoreExtension::getAttribute($this->env, $this->source, (isset($context["form"]) || array_key_exists("form", $context) ? $context["form"] : (function () { throw new RuntimeError('Variable "form" does not exist.', 66, $this->source); })()), "telef", [], "any", false, false, false, 66), 'label');
        yield "
            ";
        // line 67
        yield $this->env->getRuntime('Symfony\Component\Form\FormRenderer')->searchAndRenderBlock(CoreExtension::getAttribute($this->env, $this->source, (isset($context["form"]) || array_key_exists("form", $context) ? $context["form"] : (function () { throw new RuntimeError('Variable "form" does not exist.', 67, $this->source); })()), "telef", [], "any", false, false, false, 67), 'widget', ["attr" => ["class" => "form-control", "required" => "required", "pattern" => "[0-9]{8}", "placeholder" => "Ex : 25123456", "title" => "Le numéro doit contenir 8 chiffres"]]);
        // line 75
        yield "
            <div class=\"text-danger mt-1\" style=\"font-size: 0.95rem;\">
              ";
        // line 77
        yield $this->env->getRuntime('Symfony\Component\Form\FormRenderer')->searchAndRenderBlock(CoreExtension::getAttribute($this->env, $this->source, (isset($context["form"]) || array_key_exists("form", $context) ? $context["form"] : (function () { throw new RuntimeError('Variable "form" does not exist.', 77, $this->source); })()), "telef", [], "any", false, false, false, 77), 'errors');
        yield "
            </div>
          </div>

          ";
        // line 82
        yield "                  ";
        yield $this->env->getRuntime('Symfony\Component\Form\FormRenderer')->searchAndRenderBlock(CoreExtension::getAttribute($this->env, $this->source, (isset($context["form"]) || array_key_exists("form", $context) ? $context["form"] : (function () { throw new RuntimeError('Variable "form" does not exist.', 82, $this->source); })()), "espace", [], "any", false, false, false, 82), 'row');
        yield "

          </div>

          <div class=\"text-center mt-4\">
            <button class=\"btn btn-primary px-4\">";
        // line 87
        yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape(((array_key_exists("button_label", $context)) ? (Twig\Extension\CoreExtension::default((isset($context["button_label"]) || array_key_exists("button_label", $context) ? $context["button_label"] : (function () { throw new RuntimeError('Variable "button_label" does not exist.', 87, $this->source); })()), "Save")) : ("Save")), "html", null, true);
        yield "</button>
          </div>

          ";
        // line 90
        yield         $this->env->getRuntime('Symfony\Component\Form\FormRenderer')->renderBlock((isset($context["form"]) || array_key_exists("form", $context) ? $context["form"] : (function () { throw new RuntimeError('Variable "form" does not exist.', 90, $this->source); })()), 'form_end');
        yield "
        </div>
      </div>
    </div>
  </div>
</div>

<div class=\"text-center mt-3\">
  <a href=\"";
        // line 98
        yield $this->extensions['Symfony\Bridge\Twig\Extension\RoutingExtension']->getPath("app_organisateur_index");
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
        return "organisateur/new.html.twig";
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
        return array (  220 => 98,  209 => 90,  203 => 87,  194 => 82,  187 => 77,  183 => 75,  181 => 67,  177 => 66,  174 => 65,  167 => 60,  163 => 58,  161 => 50,  157 => 49,  154 => 48,  147 => 43,  143 => 41,  141 => 34,  137 => 33,  134 => 32,  127 => 27,  123 => 25,  121 => 18,  117 => 17,  114 => 16,  109 => 13,  100 => 6,  87 => 5,  64 => 3,  41 => 1,);
    }

    public function getSourceContext(): Source
    {
        return new Source("{% extends 'base.html.twig' %}

{% block title %}Create Organisateur{% endblock %}

{% block body %}
<div class=\"container mt-5\">
  <div class=\"row justify-content-center\">
    <div class=\"col-lg-8\">
      <div class=\"card shadow-lg border-0 rounded-4\">
        <div class=\"card-body p-5\">
          <h2 class=\"mb-4 text-center\">Create a New Organisateur</h2>

          {{ form_start(form, {'attr': {'novalidate': 'novalidate'}}) }}

          {# Nom org : requis, min 3 caractères #}
          <div class=\"mb-3\">
            {{ form_label(form.nom_org, null, {'label_attr': {'class': 'form-label'}}) }}
            {{ form_widget(form.nom_org, {
              'attr': {
                'class': 'form-control',
                'required': 'required',
                'minlength': '3',
                'placeholder': 'Nom de l\\'organisateur'
              }
            }) }}
            <div class=\"text-danger mt-1\" style=\"font-size: 0.95rem;\">
              {{ form_errors(form.nom_org) }}
            </div>
          </div>

          {# Prénom org : requis, min 3 caractères #}
          <div class=\"mb-3\">
            {{ form_label(form.prenom_org) }}
            {{ form_widget(form.prenom_org, {
              'attr': {
                'class': 'form-control',
                'required': 'required',
                'minlength': '3',
                'placeholder': 'Prénom de l\\'organisateur'
              }
            }) }}
            <div class=\"text-danger mt-1\" style=\"font-size: 0.95rem;\">
              {{ form_errors(form.prenom_org) }}
            </div>
          </div>

          {# Description org : requis, min 5 caractères #}
          <div class=\"mb-3\">
            {{ form_label(form.description_org) }}
            {{ form_widget(form.description_org, {
              'attr': {
                'class': 'form-control',
                'required': 'required',
                'minlength': '5',
                'rows': '3',
                'placeholder': 'Décrivez l\\'organisateur...'
              }
            }) }}
            <div class=\"text-danger mt-1\" style=\"font-size: 0.95rem;\">
              {{ form_errors(form.description_org) }}
            </div>
          </div>

          {# Téléphone : requis, 8 chiffres #}
          <div class=\"mb-3\">
            {{ form_label(form.telef) }}
            {{ form_widget(form.telef, {
              'attr': {
                'class': 'form-control',
                'required': 'required',
                'pattern': '[0-9]{8}',
                'placeholder': 'Ex : 25123456',
                'title': 'Le numéro doit contenir 8 chiffres'
              }
            }) }}
            <div class=\"text-danger mt-1\" style=\"font-size: 0.95rem;\">
              {{ form_errors(form.telef) }}
            </div>
          </div>

          {# Espace : requis #}
                  {{ form_row(form.espace) }}

          </div>

          <div class=\"text-center mt-4\">
            <button class=\"btn btn-primary px-4\">{{ button_label|default('Save') }}</button>
          </div>

          {{ form_end(form) }}
        </div>
      </div>
    </div>
  </div>
</div>

<div class=\"text-center mt-3\">
  <a href=\"{{ path('app_organisateur_index') }}\" class=\"btn btn-link\">← Back to list</a>
</div>
{% endblock %}
", "organisateur/new.html.twig", "C:\\wamp64\\gestion_espace_symfony\\templates\\organisateur\\new.html.twig");
    }
}
