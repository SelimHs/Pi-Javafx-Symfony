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

/* organisateur/show.html.twig */
class __TwigTemplate_a591e55de10c83d6ebf9d6ee0b61ee2d extends Template
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
        $__internal_5a27a8ba21ca79b61932376b2fa922d2->enter($__internal_5a27a8ba21ca79b61932376b2fa922d2_prof = new \Twig\Profiler\Profile($this->getTemplateName(), "template", "organisateur/show.html.twig"));

        $__internal_6f47bbe9983af81f1e7450e9a3e3768f = $this->extensions["Symfony\\Bridge\\Twig\\Extension\\ProfilerExtension"];
        $__internal_6f47bbe9983af81f1e7450e9a3e3768f->enter($__internal_6f47bbe9983af81f1e7450e9a3e3768f_prof = new \Twig\Profiler\Profile($this->getTemplateName(), "template", "organisateur/show.html.twig"));

        $this->parent = $this->loadTemplate("baseBack.html.twig", "organisateur/show.html.twig", 1);
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

        yield "Détails de l'Organisateur";
        
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
            <i class=\"pe-7s-id icon-gradient bg-amy-crisp\"></i>
          </div>
          <div>
            Détails de l'Organisateur
            <div class=\"page-title-subheading\">Toutes les informations liées à cet organisateur.</div>
          </div>
        </div>
        <div class=\"page-title-actions\">
          <a href=\"";
        // line 20
        yield $this->extensions['Symfony\Bridge\Twig\Extension\RoutingExtension']->getPath("app_organisateur_index");
        yield "\" class=\"btn btn-outline-secondary mr-2\">← Retour</a>
          <a href=\"";
        // line 21
        yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape($this->extensions['Symfony\Bridge\Twig\Extension\RoutingExtension']->getPath("app_organisateur_edit", ["id_org" => CoreExtension::getAttribute($this->env, $this->source, (isset($context["organisateur"]) || array_key_exists("organisateur", $context) ? $context["organisateur"] : (function () { throw new RuntimeError('Variable "organisateur" does not exist.', 21, $this->source); })()), "idOrg", [], "any", false, false, false, 21)]), "html", null, true);
        yield "\" class=\"btn btn-warning\">Modifier</a>
        </div>
      </div>
    </div>

    <div class=\"main-card mb-3 card\">
      <div class=\"card-body\">
        <h5 class=\"card-title\">Informations Générales</h5>
        <table class=\"table table-bordered table-striped\">
          <tbody>
            <tr>
              <th>ID</th>
              <td>";
        // line 33
        yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape(CoreExtension::getAttribute($this->env, $this->source, (isset($context["organisateur"]) || array_key_exists("organisateur", $context) ? $context["organisateur"] : (function () { throw new RuntimeError('Variable "organisateur" does not exist.', 33, $this->source); })()), "idOrg", [], "any", false, false, false, 33), "html", null, true);
        yield "</td>
            </tr>
            <tr>
              <th>Nom</th>
              <td>";
        // line 37
        yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape(CoreExtension::getAttribute($this->env, $this->source, (isset($context["organisateur"]) || array_key_exists("organisateur", $context) ? $context["organisateur"] : (function () { throw new RuntimeError('Variable "organisateur" does not exist.', 37, $this->source); })()), "nomOrg", [], "any", false, false, false, 37), "html", null, true);
        yield "</td>
            </tr>
            <tr>
              <th>Prénom</th>
              <td>";
        // line 41
        yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape(CoreExtension::getAttribute($this->env, $this->source, (isset($context["organisateur"]) || array_key_exists("organisateur", $context) ? $context["organisateur"] : (function () { throw new RuntimeError('Variable "organisateur" does not exist.', 41, $this->source); })()), "prenomOrg", [], "any", false, false, false, 41), "html", null, true);
        yield "</td>
            </tr>
            <tr>
              <th>Description</th>
              <td>";
        // line 45
        yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape(CoreExtension::getAttribute($this->env, $this->source, (isset($context["organisateur"]) || array_key_exists("organisateur", $context) ? $context["organisateur"] : (function () { throw new RuntimeError('Variable "organisateur" does not exist.', 45, $this->source); })()), "descriptionOrg", [], "any", false, false, false, 45), "html", null, true);
        yield "</td>
            </tr>
            <tr>
              <th>Téléphone</th>
              <td>";
        // line 49
        yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape(CoreExtension::getAttribute($this->env, $this->source, (isset($context["organisateur"]) || array_key_exists("organisateur", $context) ? $context["organisateur"] : (function () { throw new RuntimeError('Variable "organisateur" does not exist.', 49, $this->source); })()), "telef", [], "any", false, false, false, 49), "html", null, true);
        yield "</td>
            </tr>
            <tr>
              <th>Espace associé</th>
              <td>";
        // line 53
        yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape(CoreExtension::getAttribute($this->env, $this->source, CoreExtension::getAttribute($this->env, $this->source, (isset($context["organisateur"]) || array_key_exists("organisateur", $context) ? $context["organisateur"] : (function () { throw new RuntimeError('Variable "organisateur" does not exist.', 53, $this->source); })()), "espace", [], "any", false, false, false, 53), "nomEspace", [], "any", false, false, false, 53), "html", null, true);
        yield "</td>
            </tr>
          </tbody>
        </table>
      </div>
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
        return "organisateur/show.html.twig";
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
        return array (  170 => 53,  163 => 49,  156 => 45,  149 => 41,  142 => 37,  135 => 33,  120 => 21,  116 => 20,  100 => 6,  87 => 5,  64 => 3,  41 => 1,);
    }

    public function getSourceContext(): Source
    {
        return new Source("{% extends 'baseBack.html.twig' %}

{% block title %}Détails de l'Organisateur{% endblock %}

{% block body %}
<div class=\"app-main__outer\">
  <div class=\"app-main__inner\">
    <div class=\"app-page-title\">
      <div class=\"page-title-wrapper\">
        <div class=\"page-title-heading\">
          <div class=\"page-title-icon\">
            <i class=\"pe-7s-id icon-gradient bg-amy-crisp\"></i>
          </div>
          <div>
            Détails de l'Organisateur
            <div class=\"page-title-subheading\">Toutes les informations liées à cet organisateur.</div>
          </div>
        </div>
        <div class=\"page-title-actions\">
          <a href=\"{{ path('app_organisateur_index') }}\" class=\"btn btn-outline-secondary mr-2\">← Retour</a>
          <a href=\"{{ path('app_organisateur_edit', {'id_org': organisateur.idOrg}) }}\" class=\"btn btn-warning\">Modifier</a>
        </div>
      </div>
    </div>

    <div class=\"main-card mb-3 card\">
      <div class=\"card-body\">
        <h5 class=\"card-title\">Informations Générales</h5>
        <table class=\"table table-bordered table-striped\">
          <tbody>
            <tr>
              <th>ID</th>
              <td>{{ organisateur.idOrg }}</td>
            </tr>
            <tr>
              <th>Nom</th>
              <td>{{ organisateur.nomOrg }}</td>
            </tr>
            <tr>
              <th>Prénom</th>
              <td>{{ organisateur.prenomOrg }}</td>
            </tr>
            <tr>
              <th>Description</th>
              <td>{{ organisateur.descriptionOrg }}</td>
            </tr>
            <tr>
              <th>Téléphone</th>
              <td>{{ organisateur.telef }}</td>
            </tr>
            <tr>
              <th>Espace associé</th>
              <td>{{ organisateur.espace.nomEspace }}</td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>
  </div>
</div>
{% endblock %}
", "organisateur/show.html.twig", "C:\\wamp64\\Pi-Javafx-Symfony-selimWeb (8)\\Pi-Javafx-Symfony-selimWeb\\templates\\organisateur\\show.html.twig");
    }
}
