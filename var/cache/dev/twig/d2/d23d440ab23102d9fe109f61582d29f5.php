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

        yield "Évènements";
        
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
        <h3>Nos Évènements</h3>
      </div>
    </div>
  </div>
</div>

<div class=\"section properties\">
  <div class=\"container\">

    <!-- Barre de recherche -->
    <div class=\"row mb-4\">
      <div class=\"col-lg-12 text-center\">
        <input type=\"text\" id=\"eventSearch\" class=\"form-control w-50 mx-auto\" placeholder=\"🔍 Rechercher un évènement...\">
      </div>
    </div>

    <!-- Grille des évènements -->
    <div class=\"row properties-box\" id=\"eventGrid\">
      <!-- Carte d'ajout stylée -->
      <div class=\"col-lg-4 col-md-6 align-self-center mb-30 event-card\">
        <a href=\"";
        // line 31
        yield $this->extensions['Symfony\Bridge\Twig\Extension\RoutingExtension']->getPath("app_event_new");
        yield "\" class=\"text-decoration-none\">
          <div class=\"item add-event-card text-center shadow-lg\">
            <h5 class=\"text-white\"><i class=\"fa fa-plus fa-3x\"></i></h5>
            <p class=\"text-white mt-2\" style=\"font-size: 1.1rem; font-weight: bold;\">Ajouter un évènement</p>
          </div>
        </a>
      </div>

      <!-- Boucle des événements -->
      ";
        // line 40
        $context['_parent'] = $context;
        $context['_seq'] = CoreExtension::ensureTraversable((isset($context["events"]) || array_key_exists("events", $context) ? $context["events"] : (function () { throw new RuntimeError('Variable "events" does not exist.', 40, $this->source); })()));
        $context['_iterated'] = false;
        foreach ($context['_seq'] as $context["_key"] => $context["event"]) {
            // line 41
            yield "        <div class=\"col-lg-4 col-md-6 align-self-center mb-30 event-card\">
          <div class=\"item position-relative\">
            <a href=\"";
            // line 43
            yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape($this->extensions['Symfony\Bridge\Twig\Extension\RoutingExtension']->getPath("app_event_show", ["idEvent" => CoreExtension::getAttribute($this->env, $this->source, $context["event"], "idEvent", [], "any", false, false, false, 43)]), "html", null, true);
            yield "\">
              <img 
                src=\"";
            // line 45
            yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape($this->extensions['Symfony\Bridge\Twig\Extension\AssetExtension']->getAssetUrl(("uploads/" . ((CoreExtension::getAttribute($this->env, $this->source, $context["event"], "image", [], "any", false, false, false, 45)) ? (CoreExtension::getAttribute($this->env, $this->source, $context["event"], "image", [], "any", false, false, false, 45)) : ("exemple.jpg")))), "html", null, true);
            yield "\" 
                alt=\"";
            // line 46
            yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape(CoreExtension::getAttribute($this->env, $this->source, $context["event"], "nomEvent", [], "any", false, false, false, 46), "html", null, true);
            yield "\"
                class=\"img-fluid\"
              >
              ";
            // line 49
            if ( !CoreExtension::getAttribute($this->env, $this->source, $context["event"], "image", [], "any", false, false, false, 49)) {
                // line 50
                yield "                <span class=\"badge badge-dark position-absolute\" style=\"top: 10px; left: 10px;\">Aucune image</span>
              ";
            }
            // line 52
            yield "            </a>
            <span class=\"category\">";
            // line 53
            yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape(CoreExtension::getAttribute($this->env, $this->source, $context["event"], "nomEspace", [], "any", false, false, false, 53), "html", null, true);
            yield "</span>
            <h6>";
            // line 54
            yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape(CoreExtension::getAttribute($this->env, $this->source, $context["event"], "prix", [], "any", false, false, false, 54), "html", null, true);
            yield " Dt</h6>
            <h4><a href=\"";
            // line 55
            yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape($this->extensions['Symfony\Bridge\Twig\Extension\RoutingExtension']->getPath("app_event_show", ["idEvent" => CoreExtension::getAttribute($this->env, $this->source, $context["event"], "idEvent", [], "any", false, false, false, 55)]), "html", null, true);
            yield "\">";
            yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape(CoreExtension::getAttribute($this->env, $this->source, $context["event"], "nomEvent", [], "any", false, false, false, 55), "html", null, true);
            yield "</a></h4>
            <ul>
              <li>Date : <strong>";
            // line 57
            yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape(CoreExtension::getAttribute($this->env, $this->source, $context["event"], "date", [], "any", false, false, false, 57), "html", null, true);
            yield "</strong></li>
              <li>Visiteurs : <span>";
            // line 58
            yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape(CoreExtension::getAttribute($this->env, $this->source, $context["event"], "nbrVisiteurs", [], "any", false, false, false, 58), "html", null, true);
            yield "</span></li>
              <li>Détails : <span>";
            // line 59
            yield (((Twig\Extension\CoreExtension::length($this->env->getCharset(), CoreExtension::getAttribute($this->env, $this->source, $context["event"], "details", [], "any", false, false, false, 59)) > 40)) ? ($this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape((Twig\Extension\CoreExtension::slice($this->env->getCharset(), CoreExtension::getAttribute($this->env, $this->source, $context["event"], "details", [], "any", false, false, false, 59), 0, 40) . "..."), "html", null, true)) : ($this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape(CoreExtension::getAttribute($this->env, $this->source, $context["event"], "details", [], "any", false, false, false, 59), "html", null, true)));
            yield "</span></li>
            </ul>
            <div class=\"main-button\">
              <a href=\"";
            // line 62
            yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape($this->extensions['Symfony\Bridge\Twig\Extension\RoutingExtension']->getPath("app_event_show", ["idEvent" => CoreExtension::getAttribute($this->env, $this->source, $context["event"], "idEvent", [], "any", false, false, false, 62)]), "html", null, true);
            yield "\">Voir Détails</a>
            </div>
          </div>
        </div>
      ";
            $context['_iterated'] = true;
        }
        // line 66
        if (!$context['_iterated']) {
            // line 67
            yield "        <div class=\"col-12\">
          <div class=\"alert alert-warning text-center\">Aucun évènement trouvé.</div>
        </div>
      ";
        }
        $_parent = $context['_parent'];
        unset($context['_seq'], $context['_key'], $context['event'], $context['_parent'], $context['_iterated']);
        $context = array_intersect_key($context, $_parent) + $_parent;
        // line 71
        yield "    </div>
  </div>
</div>

<!-- JavaScript Filtrage -->
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

<!-- CSS Bonus -->
<style>
  .add-event-card {
    background: linear-gradient(135deg, #6a11cb, #2575fc);
    border-radius: 15px;
    padding: 50px 20px;
    color: white;
    min-height: 370px;
    display: flex;
    flex-direction: column;
    justify-content: center;
    transition: transform 0.3s, box-shadow 0.3s;
  }

  .add-event-card:hover {
    transform: translateY(-5px);
    box-shadow: 0 10px 20px rgba(0,0,0,0.2);
  }

  .item img {
    height: 220px;
    object-fit: cover;
    width: 100%;
    border-radius: 8px;
  }

  .category {
    display: inline-block;
    background: #f0f0f0;
    padding: 5px 10px;
    font-weight: 600;
    margin-top: 10px;
    border-radius: 5px;
    font-size: 0.9rem;
  }

  .main-button a {
    background-color: #f35525;
    color: white;
    padding: 8px 20px;
    border-radius: 30px;
    font-weight: 600;
    transition: background 0.3s ease;
  }

  .main-button a:hover {
    background-color: #d93a11;
  }
</style>
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
        return array (  224 => 71,  215 => 67,  213 => 66,  204 => 62,  198 => 59,  194 => 58,  190 => 57,  183 => 55,  179 => 54,  175 => 53,  172 => 52,  168 => 50,  166 => 49,  160 => 46,  156 => 45,  151 => 43,  147 => 41,  142 => 40,  130 => 31,  106 => 10,  100 => 6,  87 => 5,  64 => 3,  41 => 1,);
    }

    public function getSourceContext(): Source
    {
        return new Source("{% extends 'base.html.twig' %}

{% block title %}Évènements{% endblock %}

{% block body %}
<div class=\"page-heading header-text\">
  <div class=\"container\">
    <div class=\"row\">
      <div class=\"col-lg-12\">
        <span class=\"breadcrumb\"><a href=\"{{ path('app_home') }}\">Accueil</a> / Évènements</span>
        <h3>Nos Évènements</h3>
      </div>
    </div>
  </div>
</div>

<div class=\"section properties\">
  <div class=\"container\">

    <!-- Barre de recherche -->
    <div class=\"row mb-4\">
      <div class=\"col-lg-12 text-center\">
        <input type=\"text\" id=\"eventSearch\" class=\"form-control w-50 mx-auto\" placeholder=\"🔍 Rechercher un évènement...\">
      </div>
    </div>

    <!-- Grille des évènements -->
    <div class=\"row properties-box\" id=\"eventGrid\">
      <!-- Carte d'ajout stylée -->
      <div class=\"col-lg-4 col-md-6 align-self-center mb-30 event-card\">
        <a href=\"{{ path('app_event_new') }}\" class=\"text-decoration-none\">
          <div class=\"item add-event-card text-center shadow-lg\">
            <h5 class=\"text-white\"><i class=\"fa fa-plus fa-3x\"></i></h5>
            <p class=\"text-white mt-2\" style=\"font-size: 1.1rem; font-weight: bold;\">Ajouter un évènement</p>
          </div>
        </a>
      </div>

      <!-- Boucle des événements -->
      {% for event in events %}
        <div class=\"col-lg-4 col-md-6 align-self-center mb-30 event-card\">
          <div class=\"item position-relative\">
            <a href=\"{{ path('app_event_show', {'idEvent': event.idEvent}) }}\">
              <img 
                src=\"{{ asset('uploads/' ~ (event.image ? event.image : 'exemple.jpg')) }}\" 
                alt=\"{{ event.nomEvent }}\"
                class=\"img-fluid\"
              >
              {% if not event.image %}
                <span class=\"badge badge-dark position-absolute\" style=\"top: 10px; left: 10px;\">Aucune image</span>
              {% endif %}
            </a>
            <span class=\"category\">{{ event.nomEspace }}</span>
            <h6>{{ event.prix }} Dt</h6>
            <h4><a href=\"{{ path('app_event_show', {'idEvent': event.idEvent}) }}\">{{ event.nomEvent }}</a></h4>
            <ul>
              <li>Date : <strong>{{ event.date }}</strong></li>
              <li>Visiteurs : <span>{{ event.nbrVisiteurs }}</span></li>
              <li>Détails : <span>{{ event.details|length > 40 ? event.details[:40] ~ '...' : event.details }}</span></li>
            </ul>
            <div class=\"main-button\">
              <a href=\"{{ path('app_event_show', {'idEvent': event.idEvent}) }}\">Voir Détails</a>
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

<!-- JavaScript Filtrage -->
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

<!-- CSS Bonus -->
<style>
  .add-event-card {
    background: linear-gradient(135deg, #6a11cb, #2575fc);
    border-radius: 15px;
    padding: 50px 20px;
    color: white;
    min-height: 370px;
    display: flex;
    flex-direction: column;
    justify-content: center;
    transition: transform 0.3s, box-shadow 0.3s;
  }

  .add-event-card:hover {
    transform: translateY(-5px);
    box-shadow: 0 10px 20px rgba(0,0,0,0.2);
  }

  .item img {
    height: 220px;
    object-fit: cover;
    width: 100%;
    border-radius: 8px;
  }

  .category {
    display: inline-block;
    background: #f0f0f0;
    padding: 5px 10px;
    font-weight: 600;
    margin-top: 10px;
    border-radius: 5px;
    font-size: 0.9rem;
  }

  .main-button a {
    background-color: #f35525;
    color: white;
    padding: 8px 20px;
    border-radius: 30px;
    font-weight: 600;
    transition: background 0.3s ease;
  }

  .main-button a:hover {
    background-color: #d93a11;
  }
</style>
{% endblock %}
", "event/index.html.twig", "C:\\wamp64\\gestion_espace_symfony\\templates\\event\\index.html.twig");
    }
}
