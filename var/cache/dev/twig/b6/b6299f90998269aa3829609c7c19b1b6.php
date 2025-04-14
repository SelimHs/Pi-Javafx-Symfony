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

/* espace/indexBack.html.twig */
class __TwigTemplate_36e5e9f5930ba192e916d008c3f050f2 extends Template
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
        $__internal_5a27a8ba21ca79b61932376b2fa922d2->enter($__internal_5a27a8ba21ca79b61932376b2fa922d2_prof = new \Twig\Profiler\Profile($this->getTemplateName(), "template", "espace/indexBack.html.twig"));

        $__internal_6f47bbe9983af81f1e7450e9a3e3768f = $this->extensions["Symfony\\Bridge\\Twig\\Extension\\ProfilerExtension"];
        $__internal_6f47bbe9983af81f1e7450e9a3e3768f->enter($__internal_6f47bbe9983af81f1e7450e9a3e3768f_prof = new \Twig\Profiler\Profile($this->getTemplateName(), "template", "espace/indexBack.html.twig"));

        $this->parent = $this->loadTemplate("baseBack.html.twig", "espace/indexBack.html.twig", 1);
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

        yield "Espaces";
        
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
    <!-- Titre -->
    <div class=\"app-page-title\">
      <div class=\"page-title-wrapper\">
        <div class=\"page-title-heading\">
          <div class=\"page-title-icon\">
            <i class=\"pe-7s-home icon-gradient bg-happy-itmeo\"></i>
          </div>
          <div>Gestion des Espaces
            <div class=\"page-title-subheading\">Liste des espaces disponibles et leurs détails.</div>
          </div>
        </div>
        <div class=\"page-title-actions\">
          <a href=\"";
        // line 20
        yield $this->extensions['Symfony\Bridge\Twig\Extension\RoutingExtension']->getPath("dashboard_espace_new");
        yield "\" class=\"btn btn-success\">
            <i class=\"pe-7s-plus mr-2\"></i> Nouvel Espace
          </a>
        </div>
      </div>
    </div>

    <!-- Filtres -->
    <div class=\"row mb-4\">
      <div class=\"col-md-4\">
        <input type=\"text\" id=\"searchEspace\" class=\"form-control\" placeholder=\"🔍 Rechercher un espace...\">
      </div>
      <div class=\"col-md-4\">
        <select id=\"filterType\" class=\"form-control\">
          <option value=\"\">-- Tous les types --</option>
          <option value=\"culturel\">Culturel</option>
          <option value=\"familial\">Familial</option>
          <option value=\"autre\">Autre</option>
        </select>
      </div>
      <div class=\"col-md-4\">
        <select id=\"sortPrice\" class=\"form-control\">
          <option value=\"\">-- Tri par prix --</option>
          <option value=\"asc\">Prix croissant</option>
          <option value=\"desc\">Prix décroissant</option>
        </select>
      </div>
    </div>

    <!-- Liste des cartes -->
    <div class=\"row\" id=\"espaceCards\">
      ";
        // line 51
        $context['_parent'] = $context;
        $context['_seq'] = CoreExtension::ensureTraversable((isset($context["espaces"]) || array_key_exists("espaces", $context) ? $context["espaces"] : (function () { throw new RuntimeError('Variable "espaces" does not exist.', 51, $this->source); })()));
        $context['_iterated'] = false;
        foreach ($context['_seq'] as $context["_key"] => $context["espace"]) {
            // line 52
            yield "        <div class=\"col-md-6 col-xl-4 espace-card\" 
             data-nom=\"";
            // line 53
            yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape(Twig\Extension\CoreExtension::lower($this->env->getCharset(), CoreExtension::getAttribute($this->env, $this->source, $context["espace"], "nomEspace", [], "any", false, false, false, 53)), "html", null, true);
            yield "\"
             data-type=\"";
            // line 54
            yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape(Twig\Extension\CoreExtension::lower($this->env->getCharset(), CoreExtension::getAttribute($this->env, $this->source, $context["espace"], "Type_espace", [], "any", false, false, false, 54)), "html", null, true);
            yield "\"
             data-prix=\"";
            // line 55
            yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape(CoreExtension::getAttribute($this->env, $this->source, $context["espace"], "prix", [], "any", false, false, false, 55), "html", null, true);
            yield "\">
          <div class=\"card mb-4 shadow-sm\">
            <div class=\"position-relative\">
              <img 
                src=\"";
            // line 59
            yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape($this->extensions['Symfony\Bridge\Twig\Extension\AssetExtension']->getAssetUrl(("uploads/" . (( !Twig\Extension\CoreExtension::testEmpty(CoreExtension::getAttribute($this->env, $this->source, $context["espace"], "image", [], "any", false, false, false, 59))) ? (CoreExtension::getAttribute($this->env, $this->source, $context["espace"], "image", [], "any", false, false, false, 59)) : ("placeholder.jpg")))), "html", null, true);
            yield "\" 
                class=\"card-img-top\" 
                alt=\"";
            // line 61
            yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape(CoreExtension::getAttribute($this->env, $this->source, $context["espace"], "nomEspace", [], "any", false, false, false, 61), "html", null, true);
            yield "\"
              >
              ";
            // line 63
            if (Twig\Extension\CoreExtension::testEmpty(CoreExtension::getAttribute($this->env, $this->source, $context["espace"], "image", [], "any", false, false, false, 63))) {
                // line 64
                yield "                <span class=\"badge badge-secondary position-absolute\"
                      style=\"top: 10px; left: 10px; background-color: rgba(0,0,0,0.7); font-size: 0.75rem;\">
                  Placeholder
                </span>
              ";
            }
            // line 69
            yield "            </div>

            <div class=\"card-body\">
              <h5 class=\"card-title text-uppercase\">";
            // line 72
            yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape(CoreExtension::getAttribute($this->env, $this->source, $context["espace"], "nomEspace", [], "any", false, false, false, 72), "html", null, true);
            yield "</h5>
              <p class=\"card-text text-muted mb-1\">
                📍 <strong>Adresse :</strong> ";
            // line 74
            yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape(CoreExtension::getAttribute($this->env, $this->source, $context["espace"], "adresse", [], "any", false, false, false, 74), "html", null, true);
            yield "<br>
                👥 <strong>Capacité :</strong> ";
            // line 75
            yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape(CoreExtension::getAttribute($this->env, $this->source, $context["espace"], "capacite", [], "any", false, false, false, 75), "html", null, true);
            yield "<br>
                🏷️ <strong>Type :</strong> ";
            // line 76
            yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape(CoreExtension::getAttribute($this->env, $this->source, $context["espace"], "Type_espace", [], "any", false, false, false, 76), "html", null, true);
            yield "
              </p>
              <p class=\"card-text\">
                <strong>Disponibilité :</strong> ";
            // line 79
            yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape(CoreExtension::getAttribute($this->env, $this->source, $context["espace"], "disponibilite", [], "any", false, false, false, 79), "html", null, true);
            yield "
              </p>
              <div class=\"d-flex justify-content-between align-items-center\">
                <span class=\"badge badge-info p-2\">";
            // line 82
            yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape(CoreExtension::getAttribute($this->env, $this->source, $context["espace"], "prix", [], "any", false, false, false, 82), "html", null, true);
            yield " DT</span>
                <a href=\"";
            // line 83
            yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape($this->extensions['Symfony\Bridge\Twig\Extension\RoutingExtension']->getPath("dashboard_espace_show", ["idEspace" => CoreExtension::getAttribute($this->env, $this->source, $context["espace"], "idEspace", [], "any", false, false, false, 83)]), "html", null, true);
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
        // line 90
        if (!$context['_iterated']) {
            // line 91
            yield "        <div class=\"col-12\">
          <div class=\"alert alert-warning text-center\">Aucun espace trouvé.</div>
        </div>
      ";
        }
        $_parent = $context['_parent'];
        unset($context['_seq'], $context['_key'], $context['espace'], $context['_parent'], $context['_iterated']);
        $context = array_intersect_key($context, $_parent) + $_parent;
        // line 95
        yield "    </div>
  </div>
</div>

<script>
  const searchInput = document.getElementById('searchEspace');
  const filterType = document.getElementById('filterType');
  const sortPrice = document.getElementById('sortPrice');
  const cardsContainer = document.getElementById('espaceCards');

  function filterAndSortCards() {
    const query = searchInput.value.toLowerCase();
    const type = filterType.value.toLowerCase();
    const sortOrder = sortPrice.value;

    let cards = Array.from(cardsContainer.querySelectorAll('.espace-card'));

    // Filtrage
    cards.forEach(card => {
      const name = card.dataset.nom;
      const cardType = card.dataset.type;

      const matchSearch = name.includes(query);
      const matchType = !type || cardType === type;

      card.style.display = (matchSearch && matchType) ? '' : 'none';
    });

    // Tri
    cards = cards.filter(card => card.style.display !== 'none');

    if (sortOrder) {
      cards.sort((a, b) => {
        const priceA = parseFloat(a.dataset.prix);
        const priceB = parseFloat(b.dataset.prix);
        return sortOrder === 'asc' ? priceA - priceB : priceB - priceA;
      });

      // Réinjection dans le DOM
      cards.forEach(card => cardsContainer.appendChild(card));
    }
  }

  searchInput.addEventListener('input', filterAndSortCards);
  filterType.addEventListener('change', filterAndSortCards);
  sortPrice.addEventListener('change', filterAndSortCards);
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
        return "espace/indexBack.html.twig";
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
        return array (  249 => 95,  240 => 91,  238 => 90,  226 => 83,  222 => 82,  216 => 79,  210 => 76,  206 => 75,  202 => 74,  197 => 72,  192 => 69,  185 => 64,  183 => 63,  178 => 61,  173 => 59,  166 => 55,  162 => 54,  158 => 53,  155 => 52,  150 => 51,  116 => 20,  100 => 6,  87 => 5,  64 => 3,  41 => 1,);
    }

    public function getSourceContext(): Source
    {
        return new Source("{% extends 'baseBack.html.twig' %}

{% block title %}Espaces{% endblock %}

{% block body %}
<div class=\"app-main__outer\">
  <div class=\"app-main__inner\">
    <!-- Titre -->
    <div class=\"app-page-title\">
      <div class=\"page-title-wrapper\">
        <div class=\"page-title-heading\">
          <div class=\"page-title-icon\">
            <i class=\"pe-7s-home icon-gradient bg-happy-itmeo\"></i>
          </div>
          <div>Gestion des Espaces
            <div class=\"page-title-subheading\">Liste des espaces disponibles et leurs détails.</div>
          </div>
        </div>
        <div class=\"page-title-actions\">
          <a href=\"{{ path('dashboard_espace_new') }}\" class=\"btn btn-success\">
            <i class=\"pe-7s-plus mr-2\"></i> Nouvel Espace
          </a>
        </div>
      </div>
    </div>

    <!-- Filtres -->
    <div class=\"row mb-4\">
      <div class=\"col-md-4\">
        <input type=\"text\" id=\"searchEspace\" class=\"form-control\" placeholder=\"🔍 Rechercher un espace...\">
      </div>
      <div class=\"col-md-4\">
        <select id=\"filterType\" class=\"form-control\">
          <option value=\"\">-- Tous les types --</option>
          <option value=\"culturel\">Culturel</option>
          <option value=\"familial\">Familial</option>
          <option value=\"autre\">Autre</option>
        </select>
      </div>
      <div class=\"col-md-4\">
        <select id=\"sortPrice\" class=\"form-control\">
          <option value=\"\">-- Tri par prix --</option>
          <option value=\"asc\">Prix croissant</option>
          <option value=\"desc\">Prix décroissant</option>
        </select>
      </div>
    </div>

    <!-- Liste des cartes -->
    <div class=\"row\" id=\"espaceCards\">
      {% for espace in espaces %}
        <div class=\"col-md-6 col-xl-4 espace-card\" 
             data-nom=\"{{ espace.nomEspace|lower }}\"
             data-type=\"{{ espace.Type_espace|lower }}\"
             data-prix=\"{{ espace.prix }}\">
          <div class=\"card mb-4 shadow-sm\">
            <div class=\"position-relative\">
              <img 
                src=\"{{ asset('uploads/' ~ (espace.image is not empty ? espace.image : 'placeholder.jpg')) }}\" 
                class=\"card-img-top\" 
                alt=\"{{ espace.nomEspace }}\"
              >
              {% if espace.image is empty %}
                <span class=\"badge badge-secondary position-absolute\"
                      style=\"top: 10px; left: 10px; background-color: rgba(0,0,0,0.7); font-size: 0.75rem;\">
                  Placeholder
                </span>
              {% endif %}
            </div>

            <div class=\"card-body\">
              <h5 class=\"card-title text-uppercase\">{{ espace.nomEspace }}</h5>
              <p class=\"card-text text-muted mb-1\">
                📍 <strong>Adresse :</strong> {{ espace.adresse }}<br>
                👥 <strong>Capacité :</strong> {{ espace.capacite }}<br>
                🏷️ <strong>Type :</strong> {{ espace.Type_espace }}
              </p>
              <p class=\"card-text\">
                <strong>Disponibilité :</strong> {{ espace.disponibilite }}
              </p>
              <div class=\"d-flex justify-content-between align-items-center\">
                <span class=\"badge badge-info p-2\">{{ espace.prix }} DT</span>
                <a href=\"{{ path('dashboard_espace_show', {'idEspace': espace.idEspace}) }}\" class=\"btn btn-sm btn-outline-primary\">
                  Voir détails
                </a>
              </div>
            </div>
          </div>
        </div>
      {% else %}
        <div class=\"col-12\">
          <div class=\"alert alert-warning text-center\">Aucun espace trouvé.</div>
        </div>
      {% endfor %}
    </div>
  </div>
</div>

<script>
  const searchInput = document.getElementById('searchEspace');
  const filterType = document.getElementById('filterType');
  const sortPrice = document.getElementById('sortPrice');
  const cardsContainer = document.getElementById('espaceCards');

  function filterAndSortCards() {
    const query = searchInput.value.toLowerCase();
    const type = filterType.value.toLowerCase();
    const sortOrder = sortPrice.value;

    let cards = Array.from(cardsContainer.querySelectorAll('.espace-card'));

    // Filtrage
    cards.forEach(card => {
      const name = card.dataset.nom;
      const cardType = card.dataset.type;

      const matchSearch = name.includes(query);
      const matchType = !type || cardType === type;

      card.style.display = (matchSearch && matchType) ? '' : 'none';
    });

    // Tri
    cards = cards.filter(card => card.style.display !== 'none');

    if (sortOrder) {
      cards.sort((a, b) => {
        const priceA = parseFloat(a.dataset.prix);
        const priceB = parseFloat(b.dataset.prix);
        return sortOrder === 'asc' ? priceA - priceB : priceB - priceA;
      });

      // Réinjection dans le DOM
      cards.forEach(card => cardsContainer.appendChild(card));
    }
  }

  searchInput.addEventListener('input', filterAndSortCards);
  filterType.addEventListener('change', filterAndSortCards);
  sortPrice.addEventListener('change', filterAndSortCards);
</script>
{% endblock %}
", "espace/indexBack.html.twig", "C:\\wamp64\\gestion_espace_symfony\\templates\\espace\\indexBack.html.twig");
    }
}
