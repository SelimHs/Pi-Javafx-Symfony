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

/* organisateur/index.html.twig */
class __TwigTemplate_2c653635f96c9b6d6f261805a88ad590 extends Template
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
        $__internal_5a27a8ba21ca79b61932376b2fa922d2->enter($__internal_5a27a8ba21ca79b61932376b2fa922d2_prof = new \Twig\Profiler\Profile($this->getTemplateName(), "template", "organisateur/index.html.twig"));

        $__internal_6f47bbe9983af81f1e7450e9a3e3768f = $this->extensions["Symfony\\Bridge\\Twig\\Extension\\ProfilerExtension"];
        $__internal_6f47bbe9983af81f1e7450e9a3e3768f->enter($__internal_6f47bbe9983af81f1e7450e9a3e3768f_prof = new \Twig\Profiler\Profile($this->getTemplateName(), "template", "organisateur/index.html.twig"));

        $this->parent = $this->loadTemplate("baseBack.html.twig", "organisateur/index.html.twig", 1);
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

        yield "Liste des Organisateurs";
        
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
            <i class=\"pe-7s-id icon-gradient bg-sunny-morning\"></i>
          </div>
          <div>Gestion des Organisateurs
            <div class=\"page-title-subheading\">Liste des organisateurs et leurs informations.</div>
          </div>
        </div>
        <div class=\"page-title-actions\">
          <a href=\"";
        // line 19
        yield $this->extensions['Symfony\Bridge\Twig\Extension\RoutingExtension']->getPath("dashboard_organisateur_new_general");
        yield "\" class=\"btn btn-success\">
    <i class=\"pe-7s-plus mr-2\"></i> Ajouter un Organisateur
</a>

        </div>
      </div>
    </div>

    <div class=\"main-card mb-3 card\">
      <div class=\"card-header\">
        Organisateurs enregistrés
      </div>
      <div class=\"table-responsive\">
        <table class=\"align-middle mb-0 table table-striped table-hover\">
          <thead>
            <tr>
              <th class=\"text-center\">#</th>
              <th>Nom</th>
              <th>Prénom</th>
              <th>Description</th>
              <th class=\"text-center\">Téléphone</th>
              <th class=\"text-center\">Actions</th>
            </tr>
          </thead>
          <tbody>
            ";
        // line 44
        $context['_parent'] = $context;
        $context['_seq'] = CoreExtension::ensureTraversable((isset($context["organisateurs"]) || array_key_exists("organisateurs", $context) ? $context["organisateurs"] : (function () { throw new RuntimeError('Variable "organisateurs" does not exist.', 44, $this->source); })()));
        $context['_iterated'] = false;
        foreach ($context['_seq'] as $context["_key"] => $context["organisateur"]) {
            // line 45
            yield "              <tr>
                <td class=\"text-center text-muted\">#";
            // line 46
            yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape(CoreExtension::getAttribute($this->env, $this->source, $context["organisateur"], "idOrg", [], "any", false, false, false, 46), "html", null, true);
            yield "</td>
                <td>";
            // line 47
            yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape(CoreExtension::getAttribute($this->env, $this->source, $context["organisateur"], "nomOrg", [], "any", false, false, false, 47), "html", null, true);
            yield "</td>
                <td>";
            // line 48
            yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape(CoreExtension::getAttribute($this->env, $this->source, $context["organisateur"], "prenomOrg", [], "any", false, false, false, 48), "html", null, true);
            yield "</td>
                <td>";
            // line 49
            yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape(CoreExtension::getAttribute($this->env, $this->source, $context["organisateur"], "descriptionOrg", [], "any", false, false, false, 49), "html", null, true);
            yield "</td>
                <td class=\"text-center\">";
            // line 50
            yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape(CoreExtension::getAttribute($this->env, $this->source, $context["organisateur"], "telef", [], "any", false, false, false, 50), "html", null, true);
            yield "</td>
                <td class=\"text-center\">
                  <a href=\"";
            // line 52
            yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape($this->extensions['Symfony\Bridge\Twig\Extension\RoutingExtension']->getPath("app_organisateur_show", ["id_org" => CoreExtension::getAttribute($this->env, $this->source, $context["organisateur"], "idOrg", [], "any", false, false, false, 52)]), "html", null, true);
            yield "\" class=\"btn btn-sm btn-info\">Détails</a>
                  <a href=\"";
            // line 53
            yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape($this->extensions['Symfony\Bridge\Twig\Extension\RoutingExtension']->getPath("app_organisateur_edit", ["id_org" => CoreExtension::getAttribute($this->env, $this->source, $context["organisateur"], "idOrg", [], "any", false, false, false, 53)]), "html", null, true);
            yield "\" class=\"btn btn-sm btn-warning\">Modifier</a>
                </td>
              </tr>
            ";
            $context['_iterated'] = true;
        }
        // line 56
        if (!$context['_iterated']) {
            // line 57
            yield "              <tr>
                <td colspan=\"6\" class=\"text-center text-muted\">Aucun organisateur trouvé.</td>
              </tr>
            ";
        }
        $_parent = $context['_parent'];
        unset($context['_seq'], $context['_key'], $context['organisateur'], $context['_parent'], $context['_iterated']);
        $context = array_intersect_key($context, $_parent) + $_parent;
        // line 61
        yield "          </tbody>
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
        return "organisateur/index.html.twig";
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
        return array (  195 => 61,  186 => 57,  184 => 56,  176 => 53,  172 => 52,  167 => 50,  163 => 49,  159 => 48,  155 => 47,  151 => 46,  148 => 45,  143 => 44,  115 => 19,  100 => 6,  87 => 5,  64 => 3,  41 => 1,);
    }

    public function getSourceContext(): Source
    {
        return new Source("{% extends 'baseBack.html.twig' %}

{% block title %}Liste des Organisateurs{% endblock %}

{% block body %}
<div class=\"app-main__outer\">
  <div class=\"app-main__inner\">
    <div class=\"app-page-title\">
      <div class=\"page-title-wrapper\">
        <div class=\"page-title-heading\">
          <div class=\"page-title-icon\">
            <i class=\"pe-7s-id icon-gradient bg-sunny-morning\"></i>
          </div>
          <div>Gestion des Organisateurs
            <div class=\"page-title-subheading\">Liste des organisateurs et leurs informations.</div>
          </div>
        </div>
        <div class=\"page-title-actions\">
          <a href=\"{{ path('dashboard_organisateur_new_general') }}\" class=\"btn btn-success\">
    <i class=\"pe-7s-plus mr-2\"></i> Ajouter un Organisateur
</a>

        </div>
      </div>
    </div>

    <div class=\"main-card mb-3 card\">
      <div class=\"card-header\">
        Organisateurs enregistrés
      </div>
      <div class=\"table-responsive\">
        <table class=\"align-middle mb-0 table table-striped table-hover\">
          <thead>
            <tr>
              <th class=\"text-center\">#</th>
              <th>Nom</th>
              <th>Prénom</th>
              <th>Description</th>
              <th class=\"text-center\">Téléphone</th>
              <th class=\"text-center\">Actions</th>
            </tr>
          </thead>
          <tbody>
            {% for organisateur in organisateurs %}
              <tr>
                <td class=\"text-center text-muted\">#{{ organisateur.idOrg }}</td>
                <td>{{ organisateur.nomOrg }}</td>
                <td>{{ organisateur.prenomOrg }}</td>
                <td>{{ organisateur.descriptionOrg }}</td>
                <td class=\"text-center\">{{ organisateur.telef }}</td>
                <td class=\"text-center\">
                  <a href=\"{{ path('app_organisateur_show', {'id_org': organisateur.idOrg}) }}\" class=\"btn btn-sm btn-info\">Détails</a>
                  <a href=\"{{ path('app_organisateur_edit', {'id_org': organisateur.idOrg}) }}\" class=\"btn btn-sm btn-warning\">Modifier</a>
                </td>
              </tr>
            {% else %}
              <tr>
                <td colspan=\"6\" class=\"text-center text-muted\">Aucun organisateur trouvé.</td>
              </tr>
            {% endfor %}
          </tbody>
        </table>
      </div>
    </div>
  </div>
</div>
{% endblock %}
", "organisateur/index.html.twig", "C:\\wamp64\\gestion_espace_symfony\\templates\\organisateur\\index.html.twig");
    }
}
