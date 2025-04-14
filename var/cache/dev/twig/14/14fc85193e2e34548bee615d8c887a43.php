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

/* event/indexBack.html.twig */
class __TwigTemplate_cd2c0cd55845d2f2d476c7b00fbdccf5 extends Template
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
        $__internal_5a27a8ba21ca79b61932376b2fa922d2->enter($__internal_5a27a8ba21ca79b61932376b2fa922d2_prof = new \Twig\Profiler\Profile($this->getTemplateName(), "template", "event/indexBack.html.twig"));

        $__internal_6f47bbe9983af81f1e7450e9a3e3768f = $this->extensions["Symfony\\Bridge\\Twig\\Extension\\ProfilerExtension"];
        $__internal_6f47bbe9983af81f1e7450e9a3e3768f->enter($__internal_6f47bbe9983af81f1e7450e9a3e3768f_prof = new \Twig\Profiler\Profile($this->getTemplateName(), "template", "event/indexBack.html.twig"));

        $this->parent = $this->loadTemplate("baseBack.html.twig", "event/indexBack.html.twig", 1);
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
        yield "<div class=\"app-main__outer\">
  <div class=\"app-main__inner\">
    <div class=\"app-page-title\">
      <div class=\"page-title-wrapper\">
        <div class=\"page-title-heading\">
          <div class=\"page-title-icon\">
            <i class=\"pe-7s-ticket icon-gradient bg-happy-itmeo\"></i>
          </div>
          <div>Gestion des Evènements
            <div class=\"page-title-subheading\">Liste des Evènements et leurs informations.</div>
          </div>
        </div>
        <div class=\"page-title-actions\">
          <a href=\"";
        // line 19
        yield $this->extensions['Symfony\Bridge\Twig\Extension\RoutingExtension']->getPath("dashboard_event_new");
        yield "\" class=\"btn btn-success\">
            <i class=\"pe-7s-plus mr-2\"></i> Nouvel Evènement
          </a>
        </div>
      </div>
    </div>

    <div class=\"mb-4\">
      <input type=\"text\" id=\"searchEvent\" class=\"form-control\" placeholder=\"Rechercher un évènement...\">
    </div>

    <div class=\"row\" id=\"eventCards\">
      ";
        // line 31
        $context['_parent'] = $context;
        $context['_seq'] = CoreExtension::ensureTraversable((isset($context["events"]) || array_key_exists("events", $context) ? $context["events"] : (function () { throw new RuntimeError('Variable "events" does not exist.', 31, $this->source); })()));
        $context['_iterated'] = false;
        foreach ($context['_seq'] as $context["_key"] => $context["event"]) {
            // line 32
            yield "        <div class=\"col-md-6 col-xl-4 event-card\">
          <div class=\"card mb-4 shadow-sm\">
            <div class=\"position-relative\">
              <img 
                src=\"";
            // line 36
            yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape($this->extensions['Symfony\Bridge\Twig\Extension\AssetExtension']->getAssetUrl(("uploads/" . (( !Twig\Extension\CoreExtension::testEmpty(CoreExtension::getAttribute($this->env, $this->source, $context["event"], "image", [], "any", false, false, false, 36))) ? (CoreExtension::getAttribute($this->env, $this->source, $context["event"], "image", [], "any", false, false, false, 36)) : ("exemple.jpg")))), "html", null, true);
            yield "\" 
                class=\"card-img-top\" 
                alt=\"";
            // line 38
            yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape(CoreExtension::getAttribute($this->env, $this->source, $context["event"], "nomEvent", [], "any", false, false, false, 38), "html", null, true);
            yield "\"
              >
              ";
            // line 40
            if (Twig\Extension\CoreExtension::testEmpty(CoreExtension::getAttribute($this->env, $this->source, $context["event"], "image", [], "any", false, false, false, 40))) {
                // line 41
                yield "              <span class=\"badge badge-secondary position-absolute\"
                    style=\"top: 10px; left: 10px; background-color: rgba(0,0,0,0.7); font-size: 0.75rem;\">
                Placeholder
              </span>
              ";
            }
            // line 46
            yield "            </div>

            <div class=\"card-body\">
              <h5 class=\"card-title text-uppercase\">";
            // line 49
            yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape(CoreExtension::getAttribute($this->env, $this->source, $context["event"], "nomEvent", [], "any", false, false, false, 49), "html", null, true);
            yield "</h5>
              <p class=\"card-text text-muted mb-1\">
                <strong>Lieu :</strong> ";
            // line 51
            yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape(CoreExtension::getAttribute($this->env, $this->source, $context["event"], "nomEspace", [], "any", false, false, false, 51), "html", null, true);
            yield "<br>
                <strong>Date :</strong> ";
            // line 52
            yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape(CoreExtension::getAttribute($this->env, $this->source, $context["event"], "date", [], "any", false, false, false, 52), "html", null, true);
            yield "<br>
                <strong>Visiteurs :</strong> ";
            // line 53
            yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape(CoreExtension::getAttribute($this->env, $this->source, $context["event"], "nbrVisiteurs", [], "any", false, false, false, 53), "html", null, true);
            yield "
              </p>
              <p class=\"card-text\">
                ";
            // line 56
            yield (((Twig\Extension\CoreExtension::length($this->env->getCharset(), CoreExtension::getAttribute($this->env, $this->source, $context["event"], "details", [], "any", false, false, false, 56)) > 80)) ? ($this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape((Twig\Extension\CoreExtension::slice($this->env->getCharset(), CoreExtension::getAttribute($this->env, $this->source, $context["event"], "details", [], "any", false, false, false, 56), 0, 80) . "..."), "html", null, true)) : ($this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape(CoreExtension::getAttribute($this->env, $this->source, $context["event"], "details", [], "any", false, false, false, 56), "html", null, true)));
            yield "
              </p>
              <div class=\"d-flex justify-content-between align-items-center\">
                <span class=\"badge badge-info p-2\">";
            // line 59
            yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape(CoreExtension::getAttribute($this->env, $this->source, $context["event"], "prix", [], "any", false, false, false, 59), "html", null, true);
            yield " DT</span>
                <a href=\"";
            // line 60
            yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape($this->extensions['Symfony\Bridge\Twig\Extension\RoutingExtension']->getPath("dashboard_event_show", ["idEvent" => CoreExtension::getAttribute($this->env, $this->source, $context["event"], "idEvent", [], "any", false, false, false, 60)]), "html", null, true);
            yield "\" class=\"btn btn-sm btn-outline-primary\">
                  Voir détails
                </a>
              </div>
            </div>
          </div>
        </div>
      ";
            $context['_iterated'] = true;
        }
        // line 67
        if (!$context['_iterated']) {
            // line 68
            yield "        <div class=\"col-12\">
          <div class=\"alert alert-warning text-center\">Aucun évènement trouvé.</div>
        </div>
      ";
        }
        $_parent = $context['_parent'];
        unset($context['_seq'], $context['_key'], $context['event'], $context['_parent'], $context['_iterated']);
        $context = array_intersect_key($context, $_parent) + $_parent;
        // line 72
        yield "    </div>
  </div>
</div>

<script>
  const searchInput = document.getElementById('searchEvent');
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
        return "event/indexBack.html.twig";
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
        return array (  217 => 72,  208 => 68,  206 => 67,  194 => 60,  190 => 59,  184 => 56,  178 => 53,  174 => 52,  170 => 51,  165 => 49,  160 => 46,  153 => 41,  151 => 40,  146 => 38,  141 => 36,  135 => 32,  130 => 31,  115 => 19,  100 => 6,  87 => 5,  64 => 3,  41 => 1,);
    }

    public function getSourceContext(): Source
    {
        return new Source("{% extends 'baseBack.html.twig' %}

{% block title %}Events{% endblock %}

{% block body %}
<div class=\"app-main__outer\">
  <div class=\"app-main__inner\">
    <div class=\"app-page-title\">
      <div class=\"page-title-wrapper\">
        <div class=\"page-title-heading\">
          <div class=\"page-title-icon\">
            <i class=\"pe-7s-ticket icon-gradient bg-happy-itmeo\"></i>
          </div>
          <div>Gestion des Evènements
            <div class=\"page-title-subheading\">Liste des Evènements et leurs informations.</div>
          </div>
        </div>
        <div class=\"page-title-actions\">
          <a href=\"{{ path('dashboard_event_new') }}\" class=\"btn btn-success\">
            <i class=\"pe-7s-plus mr-2\"></i> Nouvel Evènement
          </a>
        </div>
      </div>
    </div>

    <div class=\"mb-4\">
      <input type=\"text\" id=\"searchEvent\" class=\"form-control\" placeholder=\"Rechercher un évènement...\">
    </div>

    <div class=\"row\" id=\"eventCards\">
      {% for event in events %}
        <div class=\"col-md-6 col-xl-4 event-card\">
          <div class=\"card mb-4 shadow-sm\">
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

            <div class=\"card-body\">
              <h5 class=\"card-title text-uppercase\">{{ event.nomEvent }}</h5>
              <p class=\"card-text text-muted mb-1\">
                <strong>Lieu :</strong> {{ event.nomEspace }}<br>
                <strong>Date :</strong> {{ event.date }}<br>
                <strong>Visiteurs :</strong> {{ event.nbrVisiteurs }}
              </p>
              <p class=\"card-text\">
                {{ event.details|length > 80 ? event.details[:80] ~ '...' : event.details }}
              </p>
              <div class=\"d-flex justify-content-between align-items-center\">
                <span class=\"badge badge-info p-2\">{{ event.prix }} DT</span>
                <a href=\"{{ path('dashboard_event_show', {'idEvent': event.idEvent}) }}\" class=\"btn btn-sm btn-outline-primary\">
                  Voir détails
                </a>
              </div>
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
  const searchInput = document.getElementById('searchEvent');
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
", "event/indexBack.html.twig", "C:\\wamp64\\Pi-Javafx-Symfony-selimWeb (8)\\Pi-Javafx-Symfony-selimWeb\\templates\\event\\indexBack.html.twig");
    }
}
