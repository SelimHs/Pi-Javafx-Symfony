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

    <!-- Barre de recherche -->
    <div class=\"row mb-4\">
      <div class=\"col-lg-12 text-center\">
        <input type=\"text\" id=\"searchEspace\" class=\"form-control w-50 mx-auto\" placeholder=\"Rechercher un espace...\">
      </div>
    </div>

    <!-- Liste des espaces -->
    <div class=\"row properties-box\" id=\"espaceGrid\">

      <!-- ✅ Carte Ajouter un Espace -->
      <div class=\"col-lg-4 col-md-6 align-self-center mb-30 properties-items espace-card\">
        <a href=\"";
        // line 32
        yield $this->extensions['Symfony\Bridge\Twig\Extension\RoutingExtension']->getPath("app_espace_new");
        yield "\" class=\"text-decoration-none\">
          <div class=\"item position-relative shadow-sm text-center d-flex flex-column justify-content-center align-items-center add-espace-card\"
               style=\"height: 100%; min-height: 420px;\">
            <i class=\"fa fa-plus-circle fa-3x text-white mb-3\"></i>
            <h5 class=\"text-white\">Créer un nouvel espace</h5>
            <p class=\"text-white\">Cliquez ici pour ajouter un nouvel espace</p>
          </div>
        </a>
      </div>

      <!-- ✅ Cartes des espaces -->
      ";
        // line 43
        $context['_parent'] = $context;
        $context['_seq'] = CoreExtension::ensureTraversable((isset($context["espaces"]) || array_key_exists("espaces", $context) ? $context["espaces"] : (function () { throw new RuntimeError('Variable "espaces" does not exist.', 43, $this->source); })()));
        $context['_iterated'] = false;
        foreach ($context['_seq'] as $context["_key"] => $context["espace"]) {
            // line 44
            yield "        <div class=\"col-lg-4 col-md-6 align-self-center mb-30 properties-items espace-card\">
          <div class=\"item position-relative shadow-sm\" style=\"transition: transform 0.3s;\">
            <a href=\"";
            // line 46
            yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape($this->extensions['Symfony\Bridge\Twig\Extension\RoutingExtension']->getPath("app_espace_show", ["idEspace" => CoreExtension::getAttribute($this->env, $this->source, $context["espace"], "idEspace", [], "any", false, false, false, 46)]), "html", null, true);
            yield "\">
              <img 
                src=\"";
            // line 48
            yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape($this->extensions['Symfony\Bridge\Twig\Extension\AssetExtension']->getAssetUrl(("uploads/" . (( !Twig\Extension\CoreExtension::testEmpty(CoreExtension::getAttribute($this->env, $this->source, $context["espace"], "image", [], "any", false, false, false, 48))) ? (CoreExtension::getAttribute($this->env, $this->source, $context["espace"], "image", [], "any", false, false, false, 48)) : ("exemple.jpg")))), "html", null, true);
            yield "\" 
                class=\"card-img-top\" 
                alt=\"";
            // line 50
            yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape(CoreExtension::getAttribute($this->env, $this->source, $context["espace"], "nomEspace", [], "any", false, false, false, 50), "html", null, true);
            yield "\"
              >
              ";
            // line 52
            if (Twig\Extension\CoreExtension::testEmpty(CoreExtension::getAttribute($this->env, $this->source, $context["espace"], "image", [], "any", false, false, false, 52))) {
                // line 53
                yield "                <span class=\"badge badge-secondary position-absolute\" style=\"top: 10px; left: 10px;\">Placeholder</span>
              ";
            }
            // line 55
            yield "            </a>
            <span class=\"category\">";
            // line 56
            yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape(CoreExtension::getAttribute($this->env, $this->source, $context["espace"], "TypeEspace", [], "any", false, false, false, 56), "html", null, true);
            yield "</span>
            <h6>";
            // line 57
            yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape(CoreExtension::getAttribute($this->env, $this->source, $context["espace"], "prix", [], "any", false, false, false, 57), "html", null, true);
            yield " Dt</h6>
            <h4>
              <a href=\"";
            // line 59
            yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape($this->extensions['Symfony\Bridge\Twig\Extension\RoutingExtension']->getPath("app_espace_show", ["idEspace" => CoreExtension::getAttribute($this->env, $this->source, $context["espace"], "idEspace", [], "any", false, false, false, 59)]), "html", null, true);
            yield "\">
                ";
            // line 60
            yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape(CoreExtension::getAttribute($this->env, $this->source, $context["espace"], "nomEspace", [], "any", false, false, false, 60), "html", null, true);
            yield "
              </a>
            </h4>
            <ul>
              <li>Adresse : <span>";
            // line 64
            yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape(CoreExtension::getAttribute($this->env, $this->source, $context["espace"], "adresse", [], "any", false, false, false, 64), "html", null, true);
            yield "</span></li>
              <li>Capacité : <span>";
            // line 65
            yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape(CoreExtension::getAttribute($this->env, $this->source, $context["espace"], "capacite", [], "any", false, false, false, 65), "html", null, true);
            yield "</span></li>
              <li>
                Disponibilité :
                <span class=\"badge ";
            // line 68
            yield (((CoreExtension::getAttribute($this->env, $this->source, $context["espace"], "disponibilite", [], "any", false, false, false, 68) == "Disponible")) ? ("bg-success") : ("bg-danger"));
            yield "\">
                  ";
            // line 69
            yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape(CoreExtension::getAttribute($this->env, $this->source, $context["espace"], "disponibilite", [], "any", false, false, false, 69), "html", null, true);
            yield "
                </span>
              </li>
            </ul>
            <div class=\"main-button d-flex justify-content-center mt-2\">
              <a href=\"";
            // line 74
            yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape($this->extensions['Symfony\Bridge\Twig\Extension\RoutingExtension']->getPath("app_espace_show", ["idEspace" => CoreExtension::getAttribute($this->env, $this->source, $context["espace"], "idEspace", [], "any", false, false, false, 74)]), "html", null, true);
            yield "\">Détails</a>
            </div>
          </div>
        </div>
      ";
            $context['_iterated'] = true;
        }
        // line 78
        if (!$context['_iterated']) {
            // line 79
            yield "        <div class=\"col-12 text-center\">
          <div class=\"alert alert-warning\">Aucun espace trouvé.</div>
        </div>
      ";
        }
        $_parent = $context['_parent'];
        unset($context['_seq'], $context['_key'], $context['espace'], $context['_parent'], $context['_iterated']);
        $context = array_intersect_key($context, $_parent) + $_parent;
        // line 83
        yield "    </div>

    <!-- ✅ Pagination dynamique JS -->
    <div class=\"row mt-4 justify-content-center\">
      <div class=\"col-auto\">
        <button class=\"btn btn-outline-primary me-2\" id=\"prevBtn\">← Précédent</button>
        <span id=\"pageInfo\" class=\"align-middle\"></span>
        <button class=\"btn btn-outline-primary ms-2\" id=\"nextBtn\">Suivant →</button>
      </div>
    </div>
  </div>
</div>

<!-- ✅ Bouton retour en haut -->
<button onclick=\"scrollToTop()\" id=\"scrollTopBtn\" title=\"Retour en haut\">⬆</button>

";
        // line 100
        yield "<style>
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

  .add-espace-card {
    background: linear-gradient(135deg, #007bff 0%, #00c6ff 100%);
    border-radius: 10px;
    color: white;
    transition: all 0.3s ease;
  }

  .add-espace-card:hover {
    transform: scale(1.03);
    box-shadow: 0 10px 20px rgba(0, 123, 255, 0.3);
  }
</style>

";
        // line 140
        yield "<script>
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
        return array (  299 => 140,  258 => 100,  240 => 83,  231 => 79,  229 => 78,  220 => 74,  212 => 69,  208 => 68,  202 => 65,  198 => 64,  191 => 60,  187 => 59,  182 => 57,  178 => 56,  175 => 55,  171 => 53,  169 => 52,  164 => 50,  159 => 48,  154 => 46,  150 => 44,  145 => 43,  131 => 32,  106 => 10,  100 => 6,  87 => 5,  64 => 3,  41 => 1,);
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

    <!-- Barre de recherche -->
    <div class=\"row mb-4\">
      <div class=\"col-lg-12 text-center\">
        <input type=\"text\" id=\"searchEspace\" class=\"form-control w-50 mx-auto\" placeholder=\"Rechercher un espace...\">
      </div>
    </div>

    <!-- Liste des espaces -->
    <div class=\"row properties-box\" id=\"espaceGrid\">

      <!-- ✅ Carte Ajouter un Espace -->
      <div class=\"col-lg-4 col-md-6 align-self-center mb-30 properties-items espace-card\">
        <a href=\"{{ path('app_espace_new') }}\" class=\"text-decoration-none\">
          <div class=\"item position-relative shadow-sm text-center d-flex flex-column justify-content-center align-items-center add-espace-card\"
               style=\"height: 100%; min-height: 420px;\">
            <i class=\"fa fa-plus-circle fa-3x text-white mb-3\"></i>
            <h5 class=\"text-white\">Créer un nouvel espace</h5>
            <p class=\"text-white\">Cliquez ici pour ajouter un nouvel espace</p>
          </div>
        </a>
      </div>

      <!-- ✅ Cartes des espaces -->
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

    <!-- ✅ Pagination dynamique JS -->
    <div class=\"row mt-4 justify-content-center\">
      <div class=\"col-auto\">
        <button class=\"btn btn-outline-primary me-2\" id=\"prevBtn\">← Précédent</button>
        <span id=\"pageInfo\" class=\"align-middle\"></span>
        <button class=\"btn btn-outline-primary ms-2\" id=\"nextBtn\">Suivant →</button>
      </div>
    </div>
  </div>
</div>

<!-- ✅ Bouton retour en haut -->
<button onclick=\"scrollToTop()\" id=\"scrollTopBtn\" title=\"Retour en haut\">⬆</button>

{# ✅ STYLES BONUS #}
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

  .add-espace-card {
    background: linear-gradient(135deg, #007bff 0%, #00c6ff 100%);
    border-radius: 10px;
    color: white;
    transition: all 0.3s ease;
  }

  .add-espace-card:hover {
    transform: scale(1.03);
    box-shadow: 0 10px 20px rgba(0, 123, 255, 0.3);
  }
</style>

{# ✅ SCRIPT JS : filtrage + pagination + retour en haut #}
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
