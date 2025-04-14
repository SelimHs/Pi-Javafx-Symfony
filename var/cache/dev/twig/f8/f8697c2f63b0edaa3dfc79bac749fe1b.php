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

/* billet/show.html.twig */
class __TwigTemplate_58725d4f79b860669b6c4c3b7eeef582 extends Template
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
        $__internal_5a27a8ba21ca79b61932376b2fa922d2->enter($__internal_5a27a8ba21ca79b61932376b2fa922d2_prof = new \Twig\Profiler\Profile($this->getTemplateName(), "template", "billet/show.html.twig"));

        $__internal_6f47bbe9983af81f1e7450e9a3e3768f = $this->extensions["Symfony\\Bridge\\Twig\\Extension\\ProfilerExtension"];
        $__internal_6f47bbe9983af81f1e7450e9a3e3768f->enter($__internal_6f47bbe9983af81f1e7450e9a3e3768f_prof = new \Twig\Profiler\Profile($this->getTemplateName(), "template", "billet/show.html.twig"));

        $this->parent = $this->loadTemplate("baseBack.html.twig", "billet/show.html.twig", 1);
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

        yield "Billet Details";
        
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

    <!-- Page Title -->
    <div class=\"app-page-title\">
      <div class=\"page-title-wrapper\">
        <div class=\"page-title-heading\">
          <div class=\"page-title-icon\">
            <i class=\"pe-7s-ticket icon-gradient bg-happy-itmeo\"></i>
          </div>
          <div>
            Détails du Billet
            <div class=\"page-title-subheading\">Toutes les informations du billet sélectionné.</div>
          </div>
        </div>
        <div class=\"page-title-actions\">
          <a href=\"";
        // line 22
        yield $this->extensions['Symfony\Bridge\Twig\Extension\RoutingExtension']->getPath("app_billet_index");
        yield "\" class=\"btn btn-outline-secondary mr-2\">
            <i class=\"pe-7s-back\"></i> Retour
          </a>
          <a href=\"";
        // line 25
        yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape($this->extensions['Symfony\Bridge\Twig\Extension\RoutingExtension']->getPath("app_billet_edit", ["idBillet" => CoreExtension::getAttribute($this->env, $this->source, (isset($context["billet"]) || array_key_exists("billet", $context) ? $context["billet"] : (function () { throw new RuntimeError('Variable "billet" does not exist.', 25, $this->source); })()), "idBillet", [], "any", false, false, false, 25)]), "html", null, true);
        yield "\" class=\"btn btn-warning mr-2\">
            <i class=\"pe-7s-note\"></i> 
          </a>

          <!-- Delete Button as Icon -->
          <form method=\"post\" action=\"";
        // line 30
        yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape($this->extensions['Symfony\Bridge\Twig\Extension\RoutingExtension']->getPath("app_billet_delete", ["idBillet" => CoreExtension::getAttribute($this->env, $this->source, (isset($context["billet"]) || array_key_exists("billet", $context) ? $context["billet"] : (function () { throw new RuntimeError('Variable "billet" does not exist.', 30, $this->source); })()), "idBillet", [], "any", false, false, false, 30)]), "html", null, true);
        yield "\" onsubmit=\"return confirm('Êtes-vous sûr de vouloir supprimer ce billet ?');\" style=\"display: inline;\">
            <input type=\"hidden\" name=\"_token\" value=\"";
        // line 31
        yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape($this->env->getRuntime('Symfony\Component\Form\FormRenderer')->renderCsrfToken(("delete" . CoreExtension::getAttribute($this->env, $this->source, (isset($context["billet"]) || array_key_exists("billet", $context) ? $context["billet"] : (function () { throw new RuntimeError('Variable "billet" does not exist.', 31, $this->source); })()), "idBillet", [], "any", false, false, false, 31))), "html", null, true);
        yield "\">
            <button class=\"btn btn-danger\" title=\"Supprimer\">
              <i class=\"pe-7s-trash\"></i>
            </button>
          </form>
        </div>
      </div>
    </div>

    <!-- Content Card -->
    <div class=\"row\">
      <div class=\"col-md-8 offset-md-2\">
        <div class=\"main-card mb-3 card\">
          <div class=\"card-body\">
            <h5 class=\"card-title\">Informations du Billet</h5>
            <table class=\"mb-0 table table-bordered table-striped\">
              <tbody>
                <tr>
                  <th>Propriétaire</th>
                  <td>";
        // line 50
        yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape(CoreExtension::getAttribute($this->env, $this->source, (isset($context["billet"]) || array_key_exists("billet", $context) ? $context["billet"] : (function () { throw new RuntimeError('Variable "billet" does not exist.', 50, $this->source); })()), "proprietaire", [], "any", false, false, false, 50), "html", null, true);
        yield "</td>
                </tr>
                <tr>
                  <th>Prix</th>
                  <td>";
        // line 54
        yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape(CoreExtension::getAttribute($this->env, $this->source, (isset($context["billet"]) || array_key_exists("billet", $context) ? $context["billet"] : (function () { throw new RuntimeError('Variable "billet" does not exist.', 54, $this->source); })()), "prix", [], "any", false, false, false, 54), "html", null, true);
        yield " Dt</td>
                </tr>
                <tr>
                  <th>Date d'Achat</th>
                  <td>";
        // line 58
        yield ((CoreExtension::getAttribute($this->env, $this->source, (isset($context["billet"]) || array_key_exists("billet", $context) ? $context["billet"] : (function () { throw new RuntimeError('Variable "billet" does not exist.', 58, $this->source); })()), "dateAchat", [], "any", false, false, false, 58)) ? ($this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape($this->extensions['Twig\Extension\CoreExtension']->formatDate(CoreExtension::getAttribute($this->env, $this->source, (isset($context["billet"]) || array_key_exists("billet", $context) ? $context["billet"] : (function () { throw new RuntimeError('Variable "billet" does not exist.', 58, $this->source); })()), "dateAchat", [], "any", false, false, false, 58), "d/m/Y H:i"), "html", null, true)) : ("—"));
        yield "</td>
                </tr>
                <tr>
                  <th>Type</th>
                  <td>
                    ";
        // line 63
        if ((CoreExtension::getAttribute($this->env, $this->source, (isset($context["billet"]) || array_key_exists("billet", $context) ? $context["billet"] : (function () { throw new RuntimeError('Variable "billet" does not exist.', 63, $this->source); })()), "type", [], "any", false, false, false, 63) == "VIP")) {
            // line 64
            yield "                      <span class=\"badge badge-danger\">VIP</span>
                    ";
        } elseif ((CoreExtension::getAttribute($this->env, $this->source,         // line 65
(isset($context["billet"]) || array_key_exists("billet", $context) ? $context["billet"] : (function () { throw new RuntimeError('Variable "billet" does not exist.', 65, $this->source); })()), "type", [], "any", false, false, false, 65) == "DUO")) {
            // line 66
            yield "                      <span class=\"badge badge-info\">DUO</span>
                    ";
        } else {
            // line 68
            yield "                      <span class=\"badge badge-success\">SIMPLE</span>
                    ";
        }
        // line 70
        yield "                  </td>
                </tr>
              </tbody>
            </table>
          </div>
        </div>
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
        return "billet/show.html.twig";
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
        return array (  195 => 70,  191 => 68,  187 => 66,  185 => 65,  182 => 64,  180 => 63,  172 => 58,  165 => 54,  158 => 50,  136 => 31,  132 => 30,  124 => 25,  118 => 22,  100 => 6,  87 => 5,  64 => 3,  41 => 1,);
    }

    public function getSourceContext(): Source
    {
        return new Source("{% extends 'baseBack.html.twig' %}

{% block title %}Billet Details{% endblock %}

{% block body %}
<div class=\"app-main__outer\">
  <div class=\"app-main__inner\">

    <!-- Page Title -->
    <div class=\"app-page-title\">
      <div class=\"page-title-wrapper\">
        <div class=\"page-title-heading\">
          <div class=\"page-title-icon\">
            <i class=\"pe-7s-ticket icon-gradient bg-happy-itmeo\"></i>
          </div>
          <div>
            Détails du Billet
            <div class=\"page-title-subheading\">Toutes les informations du billet sélectionné.</div>
          </div>
        </div>
        <div class=\"page-title-actions\">
          <a href=\"{{ path('app_billet_index') }}\" class=\"btn btn-outline-secondary mr-2\">
            <i class=\"pe-7s-back\"></i> Retour
          </a>
          <a href=\"{{ path('app_billet_edit', {'idBillet': billet.idBillet}) }}\" class=\"btn btn-warning mr-2\">
            <i class=\"pe-7s-note\"></i> 
          </a>

          <!-- Delete Button as Icon -->
          <form method=\"post\" action=\"{{ path('app_billet_delete', {'idBillet': billet.idBillet}) }}\" onsubmit=\"return confirm('Êtes-vous sûr de vouloir supprimer ce billet ?');\" style=\"display: inline;\">
            <input type=\"hidden\" name=\"_token\" value=\"{{ csrf_token('delete' ~ billet.idBillet) }}\">
            <button class=\"btn btn-danger\" title=\"Supprimer\">
              <i class=\"pe-7s-trash\"></i>
            </button>
          </form>
        </div>
      </div>
    </div>

    <!-- Content Card -->
    <div class=\"row\">
      <div class=\"col-md-8 offset-md-2\">
        <div class=\"main-card mb-3 card\">
          <div class=\"card-body\">
            <h5 class=\"card-title\">Informations du Billet</h5>
            <table class=\"mb-0 table table-bordered table-striped\">
              <tbody>
                <tr>
                  <th>Propriétaire</th>
                  <td>{{ billet.proprietaire }}</td>
                </tr>
                <tr>
                  <th>Prix</th>
                  <td>{{ billet.prix }} Dt</td>
                </tr>
                <tr>
                  <th>Date d'Achat</th>
                  <td>{{ billet.dateAchat ? billet.dateAchat|date('d/m/Y H:i') : '—' }}</td>
                </tr>
                <tr>
                  <th>Type</th>
                  <td>
                    {% if billet.type == 'VIP' %}
                      <span class=\"badge badge-danger\">VIP</span>
                    {% elseif billet.type == 'DUO' %}
                      <span class=\"badge badge-info\">DUO</span>
                    {% else %}
                      <span class=\"badge badge-success\">SIMPLE</span>
                    {% endif %}
                  </td>
                </tr>
              </tbody>
            </table>
          </div>
        </div>
      </div>
    </div>

  </div>
</div>
{% endblock %}
", "billet/show.html.twig", "C:\\wamp64\\gestion_espace_symfony\\templates\\billet\\show.html.twig");
    }
}
