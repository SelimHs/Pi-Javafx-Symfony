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

/* organisateur/newBack.html.twig */
class __TwigTemplate_f1fa761c073de0fdada17f6feb0e81a9 extends Template
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
        return "baseBack.html.twig";
    }

    protected function doDisplay(array $context, array $blocks = []): iterable
    {
        $macros = $this->macros;
        $__internal_5a27a8ba21ca79b61932376b2fa922d2 = $this->extensions["Symfony\\Bundle\\WebProfilerBundle\\Twig\\WebProfilerExtension"];
        $__internal_5a27a8ba21ca79b61932376b2fa922d2->enter($__internal_5a27a8ba21ca79b61932376b2fa922d2_prof = new \Twig\Profiler\Profile($this->getTemplateName(), "template", "organisateur/newBack.html.twig"));

        $__internal_6f47bbe9983af81f1e7450e9a3e3768f = $this->extensions["Symfony\\Bridge\\Twig\\Extension\\ProfilerExtension"];
        $__internal_6f47bbe9983af81f1e7450e9a3e3768f->enter($__internal_6f47bbe9983af81f1e7450e9a3e3768f_prof = new \Twig\Profiler\Profile($this->getTemplateName(), "template", "organisateur/newBack.html.twig"));

        $this->parent = $this->loadTemplate("baseBack.html.twig", "organisateur/newBack.html.twig", 1);
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

        yield "Ajouter un Organisateur";
        
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
        yield "<div class=\"app-main__outer\">
  <div class=\"app-main__inner\">

    <div class=\"app-page-title\">
      <div class=\"page-title-wrapper\">
        <div class=\"page-title-heading\">
          <div class=\"page-title-icon\">
            <i class=\"pe-7s-add-user icon-gradient bg-mean-fruit\"></i>
          </div>
          <div>
            Ajouter un nouvel organisateur
            <div class=\"page-title-subheading\">Remplissez le formulaire ci-dessous pour créer un nouvel organisateur.</div>
          </div>
        </div>
      </div>
    </div>

    <div class=\"main-card mb-3 card\">
      <div class=\"card-body\">
        <h5 class=\"card-title text-center\">Formulaire Organisateur</h5>

        ";
        // line 27
        yield         $this->env->getRuntime('Symfony\Component\Form\FormRenderer')->renderBlock((isset($context["form"]) || array_key_exists("form", $context) ? $context["form"] : (function () { throw new RuntimeError('Variable "form" does not exist.', 27, $this->source); })()), 'form_start', ["attr" => ["novalidate" => "novalidate"]]);
        yield "

        <div class=\"mb-3\">
          ";
        // line 30
        yield $this->env->getRuntime('Symfony\Component\Form\FormRenderer')->searchAndRenderBlock(CoreExtension::getAttribute($this->env, $this->source, (isset($context["form"]) || array_key_exists("form", $context) ? $context["form"] : (function () { throw new RuntimeError('Variable "form" does not exist.', 30, $this->source); })()), "nom_org", [], "any", false, false, false, 30), 'label');
        yield "
          ";
        // line 31
        yield $this->env->getRuntime('Symfony\Component\Form\FormRenderer')->searchAndRenderBlock(CoreExtension::getAttribute($this->env, $this->source, (isset($context["form"]) || array_key_exists("form", $context) ? $context["form"] : (function () { throw new RuntimeError('Variable "form" does not exist.', 31, $this->source); })()), "nom_org", [], "any", false, false, false, 31), 'widget', ["attr" => ["class" => "form-control", "required" => "required", "minlength" => "3"]]);
        yield "
          <div class=\"text-danger small mt-1\">";
        // line 32
        yield $this->env->getRuntime('Symfony\Component\Form\FormRenderer')->searchAndRenderBlock(CoreExtension::getAttribute($this->env, $this->source, (isset($context["form"]) || array_key_exists("form", $context) ? $context["form"] : (function () { throw new RuntimeError('Variable "form" does not exist.', 32, $this->source); })()), "nom_org", [], "any", false, false, false, 32), 'errors');
        yield "</div>
        </div>

        <div class=\"mb-3\">
          ";
        // line 36
        yield $this->env->getRuntime('Symfony\Component\Form\FormRenderer')->searchAndRenderBlock(CoreExtension::getAttribute($this->env, $this->source, (isset($context["form"]) || array_key_exists("form", $context) ? $context["form"] : (function () { throw new RuntimeError('Variable "form" does not exist.', 36, $this->source); })()), "prenom_org", [], "any", false, false, false, 36), 'label');
        yield "
          ";
        // line 37
        yield $this->env->getRuntime('Symfony\Component\Form\FormRenderer')->searchAndRenderBlock(CoreExtension::getAttribute($this->env, $this->source, (isset($context["form"]) || array_key_exists("form", $context) ? $context["form"] : (function () { throw new RuntimeError('Variable "form" does not exist.', 37, $this->source); })()), "prenom_org", [], "any", false, false, false, 37), 'widget', ["attr" => ["class" => "form-control", "required" => "required", "minlength" => "3"]]);
        yield "
          <div class=\"text-danger small mt-1\">";
        // line 38
        yield $this->env->getRuntime('Symfony\Component\Form\FormRenderer')->searchAndRenderBlock(CoreExtension::getAttribute($this->env, $this->source, (isset($context["form"]) || array_key_exists("form", $context) ? $context["form"] : (function () { throw new RuntimeError('Variable "form" does not exist.', 38, $this->source); })()), "prenom_org", [], "any", false, false, false, 38), 'errors');
        yield "</div>
        </div>

        <div class=\"mb-3\">
          ";
        // line 42
        yield $this->env->getRuntime('Symfony\Component\Form\FormRenderer')->searchAndRenderBlock(CoreExtension::getAttribute($this->env, $this->source, (isset($context["form"]) || array_key_exists("form", $context) ? $context["form"] : (function () { throw new RuntimeError('Variable "form" does not exist.', 42, $this->source); })()), "description_org", [], "any", false, false, false, 42), 'label');
        yield "
          ";
        // line 43
        yield $this->env->getRuntime('Symfony\Component\Form\FormRenderer')->searchAndRenderBlock(CoreExtension::getAttribute($this->env, $this->source, (isset($context["form"]) || array_key_exists("form", $context) ? $context["form"] : (function () { throw new RuntimeError('Variable "form" does not exist.', 43, $this->source); })()), "description_org", [], "any", false, false, false, 43), 'widget', ["attr" => ["class" => "form-control", "required" => "required", "minlength" => "5"]]);
        yield "
          <div class=\"text-danger small mt-1\">";
        // line 44
        yield $this->env->getRuntime('Symfony\Component\Form\FormRenderer')->searchAndRenderBlock(CoreExtension::getAttribute($this->env, $this->source, (isset($context["form"]) || array_key_exists("form", $context) ? $context["form"] : (function () { throw new RuntimeError('Variable "form" does not exist.', 44, $this->source); })()), "description_org", [], "any", false, false, false, 44), 'errors');
        yield "</div>
        </div>

        <div class=\"mb-3\">
          ";
        // line 48
        yield $this->env->getRuntime('Symfony\Component\Form\FormRenderer')->searchAndRenderBlock(CoreExtension::getAttribute($this->env, $this->source, (isset($context["form"]) || array_key_exists("form", $context) ? $context["form"] : (function () { throw new RuntimeError('Variable "form" does not exist.', 48, $this->source); })()), "telef", [], "any", false, false, false, 48), 'label');
        yield "
          ";
        // line 49
        yield $this->env->getRuntime('Symfony\Component\Form\FormRenderer')->searchAndRenderBlock(CoreExtension::getAttribute($this->env, $this->source, (isset($context["form"]) || array_key_exists("form", $context) ? $context["form"] : (function () { throw new RuntimeError('Variable "form" does not exist.', 49, $this->source); })()), "telef", [], "any", false, false, false, 49), 'widget', ["attr" => ["class" => "form-control", "pattern" => "[0-9]{8}", "required" => "required"]]);
        yield "
          <div class=\"text-danger small mt-1\">";
        // line 50
        yield $this->env->getRuntime('Symfony\Component\Form\FormRenderer')->searchAndRenderBlock(CoreExtension::getAttribute($this->env, $this->source, (isset($context["form"]) || array_key_exists("form", $context) ? $context["form"] : (function () { throw new RuntimeError('Variable "form" does not exist.', 50, $this->source); })()), "telef", [], "any", false, false, false, 50), 'errors');
        yield "</div>
        </div>

        ";
        // line 54
        yield "        ";
        yield $this->env->getRuntime('Symfony\Component\Form\FormRenderer')->searchAndRenderBlock(CoreExtension::getAttribute($this->env, $this->source, (isset($context["form"]) || array_key_exists("form", $context) ? $context["form"] : (function () { throw new RuntimeError('Variable "form" does not exist.', 54, $this->source); })()), "espace", [], "any", false, false, false, 54), 'row');
        yield "

        <div class=\"text-center mt-4\">
          <button class=\"btn btn-success px-4\">Créer l'organisateur</button>
        </div>

        ";
        // line 60
        yield         $this->env->getRuntime('Symfony\Component\Form\FormRenderer')->renderBlock((isset($context["form"]) || array_key_exists("form", $context) ? $context["form"] : (function () { throw new RuntimeError('Variable "form" does not exist.', 60, $this->source); })()), 'form_end');
        yield "
      </div>
    </div>

    <div class=\"text-center mt-3\">
      <a href=\"";
        // line 65
        yield $this->extensions['Symfony\Bridge\Twig\Extension\RoutingExtension']->getPath("app_organisateur_index");
        yield "\" class=\"btn btn-outline-secondary\">← Retour à la liste</a>
    </div>
  </div>
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
        return "organisateur/newBack.html.twig";
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
        return array (  206 => 65,  198 => 60,  188 => 54,  182 => 50,  178 => 49,  174 => 48,  167 => 44,  163 => 43,  159 => 42,  152 => 38,  148 => 37,  144 => 36,  137 => 32,  133 => 31,  129 => 30,  123 => 27,  100 => 6,  87 => 5,  64 => 3,  41 => 1,);
    }

    public function getSourceContext(): Source
    {
        return new Source("{% extends 'baseBack.html.twig' %}

{% block title %}Ajouter un Organisateur{% endblock %}

{% block body %}
<div class=\"app-main__outer\">
  <div class=\"app-main__inner\">

    <div class=\"app-page-title\">
      <div class=\"page-title-wrapper\">
        <div class=\"page-title-heading\">
          <div class=\"page-title-icon\">
            <i class=\"pe-7s-add-user icon-gradient bg-mean-fruit\"></i>
          </div>
          <div>
            Ajouter un nouvel organisateur
            <div class=\"page-title-subheading\">Remplissez le formulaire ci-dessous pour créer un nouvel organisateur.</div>
          </div>
        </div>
      </div>
    </div>

    <div class=\"main-card mb-3 card\">
      <div class=\"card-body\">
        <h5 class=\"card-title text-center\">Formulaire Organisateur</h5>

        {{ form_start(form, {'attr': {'novalidate': 'novalidate'}}) }}

        <div class=\"mb-3\">
          {{ form_label(form.nom_org) }}
          {{ form_widget(form.nom_org, {'attr': {'class': 'form-control', 'required': 'required', 'minlength': '3'}}) }}
          <div class=\"text-danger small mt-1\">{{ form_errors(form.nom_org) }}</div>
        </div>

        <div class=\"mb-3\">
          {{ form_label(form.prenom_org) }}
          {{ form_widget(form.prenom_org, {'attr': {'class': 'form-control', 'required': 'required', 'minlength': '3'}}) }}
          <div class=\"text-danger small mt-1\">{{ form_errors(form.prenom_org) }}</div>
        </div>

        <div class=\"mb-3\">
          {{ form_label(form.description_org) }}
          {{ form_widget(form.description_org, {'attr': {'class': 'form-control', 'required': 'required', 'minlength': '5'}}) }}
          <div class=\"text-danger small mt-1\">{{ form_errors(form.description_org) }}</div>
        </div>

        <div class=\"mb-3\">
          {{ form_label(form.telef) }}
          {{ form_widget(form.telef, {'attr': {'class': 'form-control', 'pattern': '[0-9]{8}', 'required': 'required'}}) }}
          <div class=\"text-danger small mt-1\">{{ form_errors(form.telef) }}</div>
        </div>

        {# Champ caché pour Espace si vous souhaitez le pré-remplir #}
        {{ form_row(form.espace) }}

        <div class=\"text-center mt-4\">
          <button class=\"btn btn-success px-4\">Créer l'organisateur</button>
        </div>

        {{ form_end(form) }}
      </div>
    </div>

    <div class=\"text-center mt-3\">
      <a href=\"{{ path('app_organisateur_index') }}\" class=\"btn btn-outline-secondary\">← Retour à la liste</a>
    </div>
  </div>
</div>
{% endblock %}
", "organisateur/newBack.html.twig", "C:\\wamp64\\gestion_espace_symfony\\templates\\organisateur\\newBack.html.twig");
    }
}
