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

/* event/index.html.twig */
class __TwigTemplate_44d3a72906849517f5cc99d41f1aea92 extends Template
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
        $__internal_5a27a8ba21ca79b61932376b2fa922d2->enter($__internal_5a27a8ba21ca79b61932376b2fa922d2_prof = new \Twig\Profiler\Profile($this->getTemplateName(), "template", "event/index.html.twig"));

        $__internal_6f47bbe9983af81f1e7450e9a3e3768f = $this->extensions["Symfony\\Bridge\\Twig\\Extension\\ProfilerExtension"];
        $__internal_6f47bbe9983af81f1e7450e9a3e3768f->enter($__internal_6f47bbe9983af81f1e7450e9a3e3768f_prof = new \Twig\Profiler\Profile($this->getTemplateName(), "template", "event/index.html.twig"));

        $this->parent = $this->loadTemplate("base.html.twig", "event/index.html.twig", 1);
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

        yield "Events";
        
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
        yield "<div class=\"page-heading header-text\">
  <div class=\"container\">
    <div class=\"row\">
      <div class=\"col-lg-12\">
        <span class=\"breadcrumb\"><a href=\"";
        // line 10
        yield $this->extensions['Symfony\Bridge\Twig\Extension\RoutingExtension']->getPath("app_home");
        yield "\">Accueil</a> / Évènements</span>
        <h3>Évènements</h3>
      </div>
    </div>
  </div>
</div>

<div class=\"section properties\">
  <div class=\"container\">

    <!-- Bouton Création -->
    <div class=\"row mb-4\">
      <div class=\"col-lg-12 text-center\">
        <a href=\"";
        // line 23
        yield $this->extensions['Symfony\Bridge\Twig\Extension\RoutingExtension']->getPath("app_event_new");
        yield "\" class=\"main-button custom-message-btn\">
          <i></i> + Créer un nouvel évènement
        </a>
      </div>
    </div>

    <!-- Search Bar -->
    <div class=\"row mb-4\">
      <div class=\"col-lg-12 text-center\">
        <input type=\"text\" id=\"eventSearch\" class=\"form-control w-50 mx-auto\" placeholder=\"Rechercher un évènement...\">
      </div>
    </div>

    <!-- Events Grid -->
    <div class=\"row properties-box\" id=\"eventGrid\">
      ";
        // line 38
        $context['_parent'] = $context;
        $context['_seq'] = CoreExtension::ensureTraversable((isset($context["events"]) || array_key_exists("events", $context) ? $context["events"] : (function () { throw new RuntimeError('Variable "events" does not exist.', 38, $this->source); })()));
        $context['_iterated'] = false;
        foreach ($context['_seq'] as $context["_key"] => $context["event"]) {
            // line 39
            yield "        <div class=\"col-lg-4 col-md-6 align-self-center mb-30 properties-items event-card\">
          <div class=\"item position-relative\">
            <a href=\"";
            // line 41
            yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape($this->extensions['Symfony\Bridge\Twig\Extension\RoutingExtension']->getPath("app_event_show", ["idEvent" => CoreExtension::getAttribute($this->env, $this->source, $context["event"], "idEvent", [], "any", false, false, false, 41)]), "html", null, true);
            yield "\">
              <img 
                src=\"";
            // line 43
            yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape($this->extensions['Symfony\Bridge\Twig\Extension\AssetExtension']->getAssetUrl(("uploads/" . (( !Twig\Extension\CoreExtension::testEmpty(CoreExtension::getAttribute($this->env, $this->source, $context["event"], "image", [], "any", false, false, false, 43))) ? (CoreExtension::getAttribute($this->env, $this->source, $context["event"], "image", [], "any", false, false, false, 43)) : ("exemple.jpg")))), "html", null, true);
            yield "\" 
                class=\"card-img-top\" 
                alt=\"";
            // line 45
            yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape(CoreExtension::getAttribute($this->env, $this->source, $context["event"], "nomEvent", [], "any", false, false, false, 45), "html", null, true);
            yield "\"
              >
              ";
            // line 47
            if (Twig\Extension\CoreExtension::testEmpty(CoreExtension::getAttribute($this->env, $this->source, $context["event"], "image", [], "any", false, false, false, 47))) {
                // line 48
                yield "                <span class=\"badge badge-secondary position-absolute\"
                      style=\"top: 10px; left: 10px; background-color: rgba(0,0,0,0.7); font-size: 0.75rem;\">
                  Placeholder
                </span>
              ";
            }
            // line 53
            yield "            </a>
            <span class=\"category\">";
            // line 54
            yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape(CoreExtension::getAttribute($this->env, $this->source, $context["event"], "nomEspace", [], "any", false, false, false, 54), "html", null, true);
            yield "</span>
            <h6>";
            // line 55
            yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape(CoreExtension::getAttribute($this->env, $this->source, $context["event"], "prix", [], "any", false, false, false, 55), "html", null, true);
            yield " Dt</h6>
            <h4>
              <a href=\"";
            // line 57
            yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape($this->extensions['Symfony\Bridge\Twig\Extension\RoutingExtension']->getPath("app_event_show", ["idEvent" => CoreExtension::getAttribute($this->env, $this->source, $context["event"], "idEvent", [], "any", false, false, false, 57)]), "html", null, true);
            yield "\">
                ";
            // line 58
            yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape(CoreExtension::getAttribute($this->env, $this->source, $context["event"], "nomEvent", [], "any", false, false, false, 58), "html", null, true);
            yield "
              </a>
            </h4>
            <ul>
              <li>Details: <span>";
            // line 62
            yield (((Twig\Extension\CoreExtension::length($this->env->getCharset(), CoreExtension::getAttribute($this->env, $this->source, $context["event"], "details", [], "any", false, false, false, 62)) > 40)) ? ($this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape((Twig\Extension\CoreExtension::slice($this->env->getCharset(), CoreExtension::getAttribute($this->env, $this->source, $context["event"], "details", [], "any", false, false, false, 62), 0, 40) . "..."), "html", null, true)) : ($this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape(CoreExtension::getAttribute($this->env, $this->source, $context["event"], "details", [], "any", false, false, false, 62), "html", null, true)));
            yield "</span></li>
              <li>Date: <span>";
            // line 63
            yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape(CoreExtension::getAttribute($this->env, $this->source, $context["event"], "date", [], "any", false, false, false, 63), "html", null, true);
            yield "</span></li>
              <li>Visitors: <span>";
            // line 64
            yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape(CoreExtension::getAttribute($this->env, $this->source, $context["event"], "nbrVisiteurs", [], "any", false, false, false, 64), "html", null, true);
            yield "</span></li>
            </ul>
            <div class=\"main-button\">
              <a href=\"";
            // line 67
            yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape($this->extensions['Symfony\Bridge\Twig\Extension\RoutingExtension']->getPath("app_event_show", ["idEvent" => CoreExtension::getAttribute($this->env, $this->source, $context["event"], "idEvent", [], "any", false, false, false, 67)]), "html", null, true);
            yield "\">Détails</a>
            </div>
          </div>
        </div>
      ";
            $context['_iterated'] = true;
        }
        // line 71
        if (!$context['_iterated']) {
            // line 72
            yield "        <div class=\"col-12\">
          <div class=\"alert alert-warning text-center\">Aucun évènement trouvé.</div>
        </div>
      ";
        }
        $_parent = $context['_parent'];
        unset($context['_seq'], $context['_key'], $context['event'], $context['_parent'], $context['_iterated']);
        $context = array_intersect_key($context, $_parent) + $_parent;
        // line 76
        yield "    </div>
  </div>
</div>

<script>
  const searchInput = document.getElementById('eventSearch');
  const cards = document.querySelectorAll('.event-card');

  searchInput.addEventListener('input', function () {
    const query = this.value.toLowerCase();
    cards.forEach(card => {
      const content = card.textContent.toLowerCase();
      card.style.display = content.includes(query) ? '' : 'none';
    });
  });
</script>
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
        return "event/index.html.twig";
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
        return array (  230 => 76,  221 => 72,  219 => 71,  210 => 67,  204 => 64,  200 => 63,  196 => 62,  189 => 58,  185 => 57,  180 => 55,  176 => 54,  173 => 53,  166 => 48,  164 => 47,  159 => 45,  154 => 43,  149 => 41,  145 => 39,  140 => 38,  122 => 23,  106 => 10,  100 => 6,  87 => 5,  64 => 3,  41 => 1,);
    }

    public function getSourceContext(): Source
    {
        return new Source("{% extends 'base.html.twig' %}

{% block title %}Events{% endblock %}

{% block body %}
<div class=\"page-heading header-text\">
  <div class=\"container\">
    <div class=\"row\">
      <div class=\"col-lg-12\">
        <span class=\"breadcrumb\"><a href=\"{{ path('app_home') }}\">Accueil</a> / Évènements</span>
        <h3>Évènements</h3>
      </div>
    </div>
  </div>
</div>

<div class=\"section properties\">
  <div class=\"container\">

    <!-- Bouton Création -->
    <div class=\"row mb-4\">
      <div class=\"col-lg-12 text-center\">
        <a href=\"{{ path('app_event_new') }}\" class=\"main-button custom-message-btn\">
          <i></i> + Créer un nouvel évènement
        </a>
      </div>
    </div>

    <!-- Search Bar -->
    <div class=\"row mb-4\">
      <div class=\"col-lg-12 text-center\">
        <input type=\"text\" id=\"eventSearch\" class=\"form-control w-50 mx-auto\" placeholder=\"Rechercher un évènement...\">
      </div>
    </div>

    <!-- Events Grid -->
    <div class=\"row properties-box\" id=\"eventGrid\">
      {% for event in events %}
        <div class=\"col-lg-4 col-md-6 align-self-center mb-30 properties-items event-card\">
          <div class=\"item position-relative\">
            <a href=\"{{ path('app_event_show', {'idEvent': event.idEvent}) }}\">
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
            </a>
            <span class=\"category\">{{ event.nomEspace }}</span>
            <h6>{{ event.prix }} Dt</h6>
            <h4>
              <a href=\"{{ path('app_event_show', {'idEvent': event.idEvent}) }}\">
                {{ event.nomEvent }}
              </a>
            </h4>
            <ul>
              <li>Details: <span>{{ event.details|length > 40 ? event.details[:40] ~ '...' : event.details }}</span></li>
              <li>Date: <span>{{ event.date }}</span></li>
              <li>Visitors: <span>{{ event.nbrVisiteurs }}</span></li>
            </ul>
            <div class=\"main-button\">
              <a href=\"{{ path('app_event_show', {'idEvent': event.idEvent}) }}\">Détails</a>
            </div>
          </div>
        </div>
      {% else %}
        <div class=\"col-12\">
          <div class=\"alert alert-warning text-center\">Aucun évènement trouvé.</div>
        </div>
      {% endfor %}
    </div>
  </div>
</div>

<script>
  const searchInput = document.getElementById('eventSearch');
  const cards = document.querySelectorAll('.event-card');

  searchInput.addEventListener('input', function () {
    const query = this.value.toLowerCase();
    cards.forEach(card => {
      const content = card.textContent.toLowerCase();
      card.style.display = content.includes(query) ? '' : 'none';
    });
  });
</script>
{% endblock %}
", "event/index.html.twig", "C:\\wamp64\\Pi-Javafx-Symfony-selimWeb (8)\\Pi-Javafx-Symfony-selimWeb\\templates\\event\\index.html.twig");
    }
}
