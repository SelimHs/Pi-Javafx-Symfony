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

/* event/showBack.html.twig */
class __TwigTemplate_f1131097691ec6007bc1cd8b6b0b9d43 extends Template
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
        $__internal_5a27a8ba21ca79b61932376b2fa922d2->enter($__internal_5a27a8ba21ca79b61932376b2fa922d2_prof = new \Twig\Profiler\Profile($this->getTemplateName(), "template", "event/showBack.html.twig"));

        $__internal_6f47bbe9983af81f1e7450e9a3e3768f = $this->extensions["Symfony\\Bridge\\Twig\\Extension\\ProfilerExtension"];
        $__internal_6f47bbe9983af81f1e7450e9a3e3768f->enter($__internal_6f47bbe9983af81f1e7450e9a3e3768f_prof = new \Twig\Profiler\Profile($this->getTemplateName(), "template", "event/showBack.html.twig"));

        $this->parent = $this->loadTemplate("baseBack.html.twig", "event/showBack.html.twig", 1);
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

        yield "Détails de l'Évènement";
        
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
            <i class=\"pe-7s-photo icon-gradient bg-happy-itmeo\"></i>
          </div>
          <div>
            Détails de l'Évènement
            <div class=\"page-title-subheading\">Aperçu visuel et informations complètes.</div>
          </div>
        </div>
        <div class=\"page-title-actions\">
          <a href=\"";
        // line 22
        yield $this->extensions['Symfony\Bridge\Twig\Extension\RoutingExtension']->getPath("dashboard_event_index");
        yield "\" class=\"btn btn-outline-secondary mr-2\">
            <i class=\"pe-7s-back\"></i> Retour
          </a>
          <a href=\"";
        // line 25
        yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape($this->extensions['Symfony\Bridge\Twig\Extension\RoutingExtension']->getPath("dashboard_event_edit", ["idEvent" => CoreExtension::getAttribute($this->env, $this->source, (isset($context["event"]) || array_key_exists("event", $context) ? $context["event"] : (function () { throw new RuntimeError('Variable "event" does not exist.', 25, $this->source); })()), "idEvent", [], "any", false, false, false, 25)]), "html", null, true);
        yield "\" class=\"btn btn-warning mr-2\">
            <i class=\"pe-7s-note\"></i>
          </a>
          <form method=\"post\" action=\"";
        // line 28
        yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape($this->extensions['Symfony\Bridge\Twig\Extension\RoutingExtension']->getPath("dashboard_event_delete", ["idEvent" => CoreExtension::getAttribute($this->env, $this->source, (isset($context["event"]) || array_key_exists("event", $context) ? $context["event"] : (function () { throw new RuntimeError('Variable "event" does not exist.', 28, $this->source); })()), "idEvent", [], "any", false, false, false, 28)]), "html", null, true);
        yield "\"
                onsubmit=\"return confirm('Êtes-vous sûr de vouloir supprimer cet évènement ?');\" style=\"display: inline;\">
            <input type=\"hidden\" name=\"_token\" value=\"";
        // line 30
        yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape($this->env->getRuntime('Symfony\Component\Form\FormRenderer')->renderCsrfToken(("delete" . CoreExtension::getAttribute($this->env, $this->source, (isset($context["event"]) || array_key_exists("event", $context) ? $context["event"] : (function () { throw new RuntimeError('Variable "event" does not exist.', 30, $this->source); })()), "idEvent", [], "any", false, false, false, 30))), "html", null, true);
        yield "\">
            <button class=\"btn btn-danger\" title=\"Supprimer\">
              <i class=\"pe-7s-trash\"></i>
            </button>
          </form>
        </div>
      </div>
    </div>

    <!-- Event Image -->
";
        // line 41
        yield "<div class=\"mb-4\" style=\"
display: flex;
justify-content: center;\">
<div class=\"position-relative\">
    <img 
src=\"";
        // line 46
        yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape($this->extensions['Symfony\Bridge\Twig\Extension\AssetExtension']->getAssetUrl(("uploads/" . (( !Twig\Extension\CoreExtension::testEmpty(CoreExtension::getAttribute($this->env, $this->source, (isset($context["event"]) || array_key_exists("event", $context) ? $context["event"] : (function () { throw new RuntimeError('Variable "event" does not exist.', 46, $this->source); })()), "image", [], "any", false, false, false, 46))) ? (CoreExtension::getAttribute($this->env, $this->source, (isset($context["event"]) || array_key_exists("event", $context) ? $context["event"] : (function () { throw new RuntimeError('Variable "event" does not exist.', 46, $this->source); })()), "image", [], "any", false, false, false, 46)) : ("exemple.jpg")))), "html", null, true);
        yield "\" 
class=\"card-img-top\" 
alt=\"";
        // line 48
        yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape(CoreExtension::getAttribute($this->env, $this->source, (isset($context["event"]) || array_key_exists("event", $context) ? $context["event"] : (function () { throw new RuntimeError('Variable "event" does not exist.', 48, $this->source); })()), "nomEvent", [], "any", false, false, false, 48), "html", null, true);
        yield "\"
>
";
        // line 50
        if (Twig\Extension\CoreExtension::testEmpty(CoreExtension::getAttribute($this->env, $this->source, (isset($context["event"]) || array_key_exists("event", $context) ? $context["event"] : (function () { throw new RuntimeError('Variable "event" does not exist.', 50, $this->source); })()), "image", [], "any", false, false, false, 50))) {
            // line 51
            yield "<span class=\"badge badge-secondary position-absolute\"
  style=\"top: 10px; left: 10px; background-color: rgba(0,0,0,0.7); font-size: 0.75rem;\">
Placeholder
</span>
";
        }
        // line 56
        yield "</div>
</div>


    <!-- Content Card -->
    <div class=\"row\">
      <div class=\"col-md-8 offset-md-2\">
        <div class=\"main-card mb-3 card\">
          <div class=\"card-body\">
            <h5 class=\"card-title\">Informations de l'Évènement</h5>
            <table class=\"mb-0 table table-bordered table-striped\">
              <tbody>
                <tr>
                  <th>ID</th>
                  <td>";
        // line 70
        yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape(CoreExtension::getAttribute($this->env, $this->source, (isset($context["event"]) || array_key_exists("event", $context) ? $context["event"] : (function () { throw new RuntimeError('Variable "event" does not exist.', 70, $this->source); })()), "idEvent", [], "any", false, false, false, 70), "html", null, true);
        yield "</td>
                </tr>
                <tr>
                  <th>Nom</th>
                  <td>";
        // line 74
        yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape(CoreExtension::getAttribute($this->env, $this->source, (isset($context["event"]) || array_key_exists("event", $context) ? $context["event"] : (function () { throw new RuntimeError('Variable "event" does not exist.', 74, $this->source); })()), "nomEvent", [], "any", false, false, false, 74), "html", null, true);
        yield "</td>
                </tr>
                <tr>
                  <th>Prix</th>
                  <td>";
        // line 78
        yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape(CoreExtension::getAttribute($this->env, $this->source, (isset($context["event"]) || array_key_exists("event", $context) ? $context["event"] : (function () { throw new RuntimeError('Variable "event" does not exist.', 78, $this->source); })()), "prix", [], "any", false, false, false, 78), "html", null, true);
        yield " DT</td>
                </tr>
                <tr>
                  <th>Détails</th>
                  <td>";
        // line 82
        yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape(CoreExtension::getAttribute($this->env, $this->source, (isset($context["event"]) || array_key_exists("event", $context) ? $context["event"] : (function () { throw new RuntimeError('Variable "event" does not exist.', 82, $this->source); })()), "details", [], "any", false, false, false, 82), "html", null, true);
        yield "</td>
                </tr>
                <tr>
                  <th>Date</th>
                  <td>";
        // line 86
        yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape(CoreExtension::getAttribute($this->env, $this->source, (isset($context["event"]) || array_key_exists("event", $context) ? $context["event"] : (function () { throw new RuntimeError('Variable "event" does not exist.', 86, $this->source); })()), "date", [], "any", false, false, false, 86), "html", null, true);
        yield "</td>
                </tr>
                <tr>
                  <th>Nombre de Visiteurs</th>
                  <td>";
        // line 90
        yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape(CoreExtension::getAttribute($this->env, $this->source, (isset($context["event"]) || array_key_exists("event", $context) ? $context["event"] : (function () { throw new RuntimeError('Variable "event" does not exist.', 90, $this->source); })()), "nbrVisiteurs", [], "any", false, false, false, 90), "html", null, true);
        yield "</td>
                </tr>
                <tr>
                  <th>Espace</th>
                  <td>";
        // line 94
        yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape(CoreExtension::getAttribute($this->env, $this->source, (isset($context["event"]) || array_key_exists("event", $context) ? $context["event"] : (function () { throw new RuntimeError('Variable "event" does not exist.', 94, $this->source); })()), "nomEspace", [], "any", false, false, false, 94), "html", null, true);
        yield "</td>
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
        return "event/showBack.html.twig";
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
        return array (  232 => 94,  225 => 90,  218 => 86,  211 => 82,  204 => 78,  197 => 74,  190 => 70,  174 => 56,  167 => 51,  165 => 50,  160 => 48,  155 => 46,  148 => 41,  135 => 30,  130 => 28,  124 => 25,  118 => 22,  100 => 6,  87 => 5,  64 => 3,  41 => 1,);
    }

    public function getSourceContext(): Source
    {
        return new Source("{% extends 'baseBack.html.twig' %}

{% block title %}Détails de l'Évènement{% endblock %}

{% block body %}
<div class=\"app-main__outer\">
  <div class=\"app-main__inner\">

    <!-- Page Title -->
    <div class=\"app-page-title\">
      <div class=\"page-title-wrapper\">
        <div class=\"page-title-heading\">
          <div class=\"page-title-icon\">
            <i class=\"pe-7s-photo icon-gradient bg-happy-itmeo\"></i>
          </div>
          <div>
            Détails de l'Évènement
            <div class=\"page-title-subheading\">Aperçu visuel et informations complètes.</div>
          </div>
        </div>
        <div class=\"page-title-actions\">
          <a href=\"{{ path('dashboard_event_index') }}\" class=\"btn btn-outline-secondary mr-2\">
            <i class=\"pe-7s-back\"></i> Retour
          </a>
          <a href=\"{{ path('dashboard_event_edit', {'idEvent': event.idEvent}) }}\" class=\"btn btn-warning mr-2\">
            <i class=\"pe-7s-note\"></i>
          </a>
          <form method=\"post\" action=\"{{ path('dashboard_event_delete', {'idEvent': event.idEvent}) }}\"
                onsubmit=\"return confirm('Êtes-vous sûr de vouloir supprimer cet évènement ?');\" style=\"display: inline;\">
            <input type=\"hidden\" name=\"_token\" value=\"{{ csrf_token('delete' ~ event.idEvent) }}\">
            <button class=\"btn btn-danger\" title=\"Supprimer\">
              <i class=\"pe-7s-trash\"></i>
            </button>
          </form>
        </div>
      </div>
    </div>

    <!-- Event Image -->
{# --- EVENT IMAGE HERO --- #}
<div class=\"mb-4\" style=\"
display: flex;
justify-content: center;\">
<div class=\"position-relative\">
    <img 
src=\"{{ asset('uploads/' ~ (event.image is not empty ? event.image : 'exemple.jpg')) }}\" 
class=\"card-img-top\" 
alt=\"{{ event.nomEvent }}\"
>
{% if event.image is empty %}
<span class=\"badge badge-secondary position-absolute\"
  style=\"top: 10px; left: 10px; background-color: rgba(0,0,0,0.7); font-size: 0.75rem;\">
Placeholder
</span>
{% endif %}
</div>
</div>


    <!-- Content Card -->
    <div class=\"row\">
      <div class=\"col-md-8 offset-md-2\">
        <div class=\"main-card mb-3 card\">
          <div class=\"card-body\">
            <h5 class=\"card-title\">Informations de l'Évènement</h5>
            <table class=\"mb-0 table table-bordered table-striped\">
              <tbody>
                <tr>
                  <th>ID</th>
                  <td>{{ event.idEvent }}</td>
                </tr>
                <tr>
                  <th>Nom</th>
                  <td>{{ event.nomEvent }}</td>
                </tr>
                <tr>
                  <th>Prix</th>
                  <td>{{ event.prix }} DT</td>
                </tr>
                <tr>
                  <th>Détails</th>
                  <td>{{ event.details }}</td>
                </tr>
                <tr>
                  <th>Date</th>
                  <td>{{ event.date }}</td>
                </tr>
                <tr>
                  <th>Nombre de Visiteurs</th>
                  <td>{{ event.nbrVisiteurs }}</td>
                </tr>
                <tr>
                  <th>Espace</th>
                  <td>{{ event.nomEspace }}</td>
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
", "event/showBack.html.twig", "C:\\wamp64\\gestion_espace_symfony\\templates\\event\\showBack.html.twig");
    }
}
