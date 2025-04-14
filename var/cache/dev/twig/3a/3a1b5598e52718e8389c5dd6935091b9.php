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

/* espace/index.html.twig */
class __TwigTemplate_473970077b12a9795feed68d13e0e0e4 extends Template
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
        $__internal_5a27a8ba21ca79b61932376b2fa922d2->enter($__internal_5a27a8ba21ca79b61932376b2fa922d2_prof = new \Twig\Profiler\Profile($this->getTemplateName(), "template", "espace/index.html.twig"));

        $__internal_6f47bbe9983af81f1e7450e9a3e3768f = $this->extensions["Symfony\\Bridge\\Twig\\Extension\\ProfilerExtension"];
        $__internal_6f47bbe9983af81f1e7450e9a3e3768f->enter($__internal_6f47bbe9983af81f1e7450e9a3e3768f_prof = new \Twig\Profiler\Profile($this->getTemplateName(), "template", "espace/index.html.twig"));

        $this->parent = $this->loadTemplate("base.html.twig", "espace/index.html.twig", 1);
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
        yield "<div class=\"page-heading header-text\">
  <div class=\"container\">
    <div class=\"row\">
      <div class=\"col-lg-12\">
        <span class=\"breadcrumb\"><a href=\"";
        // line 10
        yield $this->extensions['Symfony\Bridge\Twig\Extension\RoutingExtension']->getPath("app_home");
        yield "\">Accueil</a> / Espaces</span>
        <h3>Espaces</h3>
      </div>
    </div>
  </div>
</div>

<div class=\"section properties\">
  <div class=\"container\">

    <!-- Bouton de création -->
    <div class=\"row mb-4\">
      <div class=\"col-lg-12 text-center\">
        <a href=\"";
        // line 23
        yield $this->extensions['Symfony\Bridge\Twig\Extension\RoutingExtension']->getPath("app_espace_new");
        yield "\" class=\"main-button custom-message-btn\">
          <i class=\"fa fa-plus-circle\"></i> Créer un nouvel espace
        </a>
      </div>
    </div>

    <!-- Barre de recherche -->
    <div class=\"row mb-4\">
      <div class=\"col-lg-12 text-center\">
        <input type=\"text\" id=\"searchEspace\" class=\"form-control w-50 mx-auto\" placeholder=\"Rechercher un espace...\">
      </div>
    </div>

    <!-- Liste des espaces -->
    <div class=\"row properties-box\" id=\"espaceGrid\">
      ";
        // line 38
        $context['_parent'] = $context;
        $context['_seq'] = CoreExtension::ensureTraversable((isset($context["espaces"]) || array_key_exists("espaces", $context) ? $context["espaces"] : (function () { throw new RuntimeError('Variable "espaces" does not exist.', 38, $this->source); })()));
        $context['_iterated'] = false;
        foreach ($context['_seq'] as $context["_key"] => $context["espace"]) {
            // line 39
            yield "        <div class=\"col-lg-4 col-md-6 align-self-center mb-30 properties-items espace-card\">
          <div class=\"item position-relative shadow-sm\" style=\"transition: transform 0.3s;\">
            <a href=\"";
            // line 41
            yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape($this->extensions['Symfony\Bridge\Twig\Extension\RoutingExtension']->getPath("app_espace_show", ["idEspace" => CoreExtension::getAttribute($this->env, $this->source, $context["espace"], "idEspace", [], "any", false, false, false, 41)]), "html", null, true);
            yield "\">
              <img 
                src=\"";
            // line 43
            yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape($this->extensions['Symfony\Bridge\Twig\Extension\AssetExtension']->getAssetUrl(("uploads/" . (( !Twig\Extension\CoreExtension::testEmpty(CoreExtension::getAttribute($this->env, $this->source, $context["espace"], "image", [], "any", false, false, false, 43))) ? (CoreExtension::getAttribute($this->env, $this->source, $context["espace"], "image", [], "any", false, false, false, 43)) : ("exemple.jpg")))), "html", null, true);
            yield "\" 
                class=\"card-img-top\" 
                alt=\"";
            // line 45
            yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape(CoreExtension::getAttribute($this->env, $this->source, $context["espace"], "nomEspace", [], "any", false, false, false, 45), "html", null, true);
            yield "\"
              >
              ";
            // line 47
            if (Twig\Extension\CoreExtension::testEmpty(CoreExtension::getAttribute($this->env, $this->source, $context["espace"], "image", [], "any", false, false, false, 47))) {
                // line 48
                yield "                <span class=\"badge badge-secondary position-absolute\" style=\"top: 10px; left: 10px;\">Placeholder</span>
              ";
            }
            // line 50
            yield "            </a>
            <span class=\"category\">";
            // line 51
            yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape(CoreExtension::getAttribute($this->env, $this->source, $context["espace"], "TypeEspace", [], "any", false, false, false, 51), "html", null, true);
            yield "</span>
            <h6>";
            // line 52
            yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape(CoreExtension::getAttribute($this->env, $this->source, $context["espace"], "prix", [], "any", false, false, false, 52), "html", null, true);
            yield " Dt</h6>
            <h4>
              <a href=\"";
            // line 54
            yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape($this->extensions['Symfony\Bridge\Twig\Extension\RoutingExtension']->getPath("app_espace_show", ["idEspace" => CoreExtension::getAttribute($this->env, $this->source, $context["espace"], "idEspace", [], "any", false, false, false, 54)]), "html", null, true);
            yield "\">
                ";
            // line 55
            yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape(CoreExtension::getAttribute($this->env, $this->source, $context["espace"], "nomEspace", [], "any", false, false, false, 55), "html", null, true);
            yield "
              </a>
            </h4>
            <ul>
              <li>Adresse : <span>";
            // line 59
            yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape(CoreExtension::getAttribute($this->env, $this->source, $context["espace"], "adresse", [], "any", false, false, false, 59), "html", null, true);
            yield "</span></li>
              <li>Capacité : <span>";
            // line 60
            yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape(CoreExtension::getAttribute($this->env, $this->source, $context["espace"], "capacite", [], "any", false, false, false, 60), "html", null, true);
            yield "</span></li>
              <li>
                Disponibilité :
                <span class=\"badge ";
            // line 63
            yield (((CoreExtension::getAttribute($this->env, $this->source, $context["espace"], "disponibilite", [], "any", false, false, false, 63) == "Disponible")) ? ("bg-success") : ("bg-danger"));
            yield "\">
                  ";
            // line 64
            yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape(CoreExtension::getAttribute($this->env, $this->source, $context["espace"], "disponibilite", [], "any", false, false, false, 64), "html", null, true);
            yield "
                </span>
              </li>
            </ul>
            <div class=\"main-button d-flex justify-content-center mt-2\">
              <a href=\"";
            // line 69
            yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape($this->extensions['Symfony\Bridge\Twig\Extension\RoutingExtension']->getPath("app_espace_show", ["idEspace" => CoreExtension::getAttribute($this->env, $this->source, $context["espace"], "idEspace", [], "any", false, false, false, 69)]), "html", null, true);
            yield "\">Détails</a>
            </div>
          </div>
        </div>
      ";
            $context['_iterated'] = true;
        }
        // line 73
        if (!$context['_iterated']) {
            // line 74
            yield "        <div class=\"col-12 text-center\">
          <div class=\"alert alert-warning\">Aucun espace trouvé.</div>
        </div>
      ";
        }
        $_parent = $context['_parent'];
        unset($context['_seq'], $context['_key'], $context['espace'], $context['_parent'], $context['_iterated']);
        $context = array_intersect_key($context, $_parent) + $_parent;
        // line 78
        yield "    </div>

    <!-- Pagination dynamique JS -->
    <div class=\"row mt-4 justify-content-center\">
      <div class=\"col-auto\">
        <button class=\"btn btn-outline-primary me-2\" id=\"prevBtn\">← Précédent</button>
        <span id=\"pageInfo\" class=\"align-middle\"></span>
        <button class=\"btn btn-outline-primary ms-2\" id=\"nextBtn\">Suivant →</button>
      </div>
    </div>
  </div>
</div>

<!-- Bouton retour en haut -->
<button onclick=\"scrollToTop()\" id=\"scrollTopBtn\" title=\"Retour en haut\">⬆</button>

<style>
  .espace-card .item:hover {
    transform: translateY(-5px);
    box-shadow: 0 0 12px rgba(0,0,0,0.15);
  }

  #scrollTopBtn {
    position: fixed;
    bottom: 30px;
    right: 30px;
    display: none;
    z-index: 999;
    background-color: #007bff;
    color: white;
    padding: 12px 15px;
    border: none;
    border-radius: 50%;
    font-size: 20px;
    cursor: pointer;
    transition: all 0.3s ease;
  }

  #scrollTopBtn:hover {
    background-color: #0056b3;
  }
</style>

<script>
  const searchInput = document.getElementById('searchEspace');
  const cards = Array.from(document.querySelectorAll('.espace-card'));
  const itemsPerPage = 6;
  let currentPage = 1;

  function updatePagination() {
    const query = searchInput.value.toLowerCase();
    const filteredCards = cards.filter(card => card.textContent.toLowerCase().includes(query));
    
    filteredCards.forEach((card, index) => {
      const start = (currentPage - 1) * itemsPerPage;
      const end = currentPage * itemsPerPage;
      card.style.display = index >= start && index < end ? '' : 'none';
    });

    const totalPages = Math.ceil(filteredCards.length / itemsPerPage);
    document.getElementById('pageInfo').textContent = `Page \${currentPage} / \${totalPages || 1}`;
    document.getElementById('prevBtn').disabled = currentPage === 1;
    document.getElementById('nextBtn').disabled = currentPage === totalPages;
  }

  searchInput.addEventListener('input', () => {
    currentPage = 1;
    updatePagination();
  });

  document.getElementById('prevBtn').addEventListener('click', () => {
    currentPage--;
    updatePagination();
  });

  document.getElementById('nextBtn').addEventListener('click', () => {
    currentPage++;
    updatePagination();
  });

  // Scroll to top button
  const scrollBtn = document.getElementById(\"scrollTopBtn\");
  window.onscroll = () => {
    scrollBtn.style.display = (document.body.scrollTop > 100 || document.documentElement.scrollTop > 100) ? \"block\" : \"none\";
  };

  function scrollToTop() {
    window.scrollTo({ top: 0, behavior: 'smooth' });
  }

  updatePagination();
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
        return "espace/index.html.twig";
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
        return array (  235 => 78,  226 => 74,  224 => 73,  215 => 69,  207 => 64,  203 => 63,  197 => 60,  193 => 59,  186 => 55,  182 => 54,  177 => 52,  173 => 51,  170 => 50,  166 => 48,  164 => 47,  159 => 45,  154 => 43,  149 => 41,  145 => 39,  140 => 38,  122 => 23,  106 => 10,  100 => 6,  87 => 5,  64 => 3,  41 => 1,);
    }

    public function getSourceContext(): Source
    {
        return new Source("{% extends 'base.html.twig' %}

{% block title %}Espaces{% endblock %}

{% block body %}
<div class=\"page-heading header-text\">
  <div class=\"container\">
    <div class=\"row\">
      <div class=\"col-lg-12\">
        <span class=\"breadcrumb\"><a href=\"{{ path('app_home') }}\">Accueil</a> / Espaces</span>
        <h3>Espaces</h3>
      </div>
    </div>
  </div>
</div>

<div class=\"section properties\">
  <div class=\"container\">

    <!-- Bouton de création -->
    <div class=\"row mb-4\">
      <div class=\"col-lg-12 text-center\">
        <a href=\"{{ path('app_espace_new') }}\" class=\"main-button custom-message-btn\">
          <i class=\"fa fa-plus-circle\"></i> Créer un nouvel espace
        </a>
      </div>
    </div>

    <!-- Barre de recherche -->
    <div class=\"row mb-4\">
      <div class=\"col-lg-12 text-center\">
        <input type=\"text\" id=\"searchEspace\" class=\"form-control w-50 mx-auto\" placeholder=\"Rechercher un espace...\">
      </div>
    </div>

    <!-- Liste des espaces -->
    <div class=\"row properties-box\" id=\"espaceGrid\">
      {% for espace in espaces %}
        <div class=\"col-lg-4 col-md-6 align-self-center mb-30 properties-items espace-card\">
          <div class=\"item position-relative shadow-sm\" style=\"transition: transform 0.3s;\">
            <a href=\"{{ path('app_espace_show', {'idEspace': espace.idEspace}) }}\">
              <img 
                src=\"{{ asset('uploads/' ~ (espace.image is not empty ? espace.image : 'exemple.jpg')) }}\" 
                class=\"card-img-top\" 
                alt=\"{{ espace.nomEspace }}\"
              >
              {% if espace.image is empty %}
                <span class=\"badge badge-secondary position-absolute\" style=\"top: 10px; left: 10px;\">Placeholder</span>
              {% endif %}
            </a>
            <span class=\"category\">{{ espace.TypeEspace }}</span>
            <h6>{{ espace.prix }} Dt</h6>
            <h4>
              <a href=\"{{ path('app_espace_show', {'idEspace': espace.idEspace}) }}\">
                {{ espace.nomEspace }}
              </a>
            </h4>
            <ul>
              <li>Adresse : <span>{{ espace.adresse }}</span></li>
              <li>Capacité : <span>{{ espace.capacite }}</span></li>
              <li>
                Disponibilité :
                <span class=\"badge {{ espace.disponibilite == 'Disponible' ? 'bg-success' : 'bg-danger' }}\">
                  {{ espace.disponibilite }}
                </span>
              </li>
            </ul>
            <div class=\"main-button d-flex justify-content-center mt-2\">
              <a href=\"{{ path('app_espace_show', {'idEspace': espace.idEspace}) }}\">Détails</a>
            </div>
          </div>
        </div>
      {% else %}
        <div class=\"col-12 text-center\">
          <div class=\"alert alert-warning\">Aucun espace trouvé.</div>
        </div>
      {% endfor %}
    </div>

    <!-- Pagination dynamique JS -->
    <div class=\"row mt-4 justify-content-center\">
      <div class=\"col-auto\">
        <button class=\"btn btn-outline-primary me-2\" id=\"prevBtn\">← Précédent</button>
        <span id=\"pageInfo\" class=\"align-middle\"></span>
        <button class=\"btn btn-outline-primary ms-2\" id=\"nextBtn\">Suivant →</button>
      </div>
    </div>
  </div>
</div>

<!-- Bouton retour en haut -->
<button onclick=\"scrollToTop()\" id=\"scrollTopBtn\" title=\"Retour en haut\">⬆</button>

<style>
  .espace-card .item:hover {
    transform: translateY(-5px);
    box-shadow: 0 0 12px rgba(0,0,0,0.15);
  }

  #scrollTopBtn {
    position: fixed;
    bottom: 30px;
    right: 30px;
    display: none;
    z-index: 999;
    background-color: #007bff;
    color: white;
    padding: 12px 15px;
    border: none;
    border-radius: 50%;
    font-size: 20px;
    cursor: pointer;
    transition: all 0.3s ease;
  }

  #scrollTopBtn:hover {
    background-color: #0056b3;
  }
</style>

<script>
  const searchInput = document.getElementById('searchEspace');
  const cards = Array.from(document.querySelectorAll('.espace-card'));
  const itemsPerPage = 6;
  let currentPage = 1;

  function updatePagination() {
    const query = searchInput.value.toLowerCase();
    const filteredCards = cards.filter(card => card.textContent.toLowerCase().includes(query));
    
    filteredCards.forEach((card, index) => {
      const start = (currentPage - 1) * itemsPerPage;
      const end = currentPage * itemsPerPage;
      card.style.display = index >= start && index < end ? '' : 'none';
    });

    const totalPages = Math.ceil(filteredCards.length / itemsPerPage);
    document.getElementById('pageInfo').textContent = `Page \${currentPage} / \${totalPages || 1}`;
    document.getElementById('prevBtn').disabled = currentPage === 1;
    document.getElementById('nextBtn').disabled = currentPage === totalPages;
  }

  searchInput.addEventListener('input', () => {
    currentPage = 1;
    updatePagination();
  });

  document.getElementById('prevBtn').addEventListener('click', () => {
    currentPage--;
    updatePagination();
  });

  document.getElementById('nextBtn').addEventListener('click', () => {
    currentPage++;
    updatePagination();
  });

  // Scroll to top button
  const scrollBtn = document.getElementById(\"scrollTopBtn\");
  window.onscroll = () => {
    scrollBtn.style.display = (document.body.scrollTop > 100 || document.documentElement.scrollTop > 100) ? \"block\" : \"none\";
  };

  function scrollToTop() {
    window.scrollTo({ top: 0, behavior: 'smooth' });
  }

  updatePagination();
</script>
{% endblock %}
", "espace/index.html.twig", "C:\\wamp64\\gestion_espace_symfony\\templates\\espace\\index.html.twig");
    }
}
