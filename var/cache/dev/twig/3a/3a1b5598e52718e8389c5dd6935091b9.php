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

    <!-- Bouton Création -->
    <div class=\"row mb-4\">
      <div class=\"col-lg-12 text-center\">
        <a href=\"";
        // line 23
        yield $this->extensions['Symfony\Bridge\Twig\Extension\RoutingExtension']->getPath("app_espace_new");
        yield "\" class=\"main-button custom-message-btn\">
          <i></i> + Créer un nouvel espace
        </a>
      </div>
    </div>

    <!-- 🔍 Search Bar -->
    <div class=\"row mb-4\">
      <div class=\"col-lg-12 text-center\">
        <input type=\"text\" id=\"searchEspace\" class=\"form-control w-50 mx-auto\" placeholder=\"Rechercher un espace...\">
      </div>
    </div>

    <div class=\"row properties-box\" id=\"espaceGrid\">
      ";
        // line 37
        $context['_parent'] = $context;
        $context['_seq'] = CoreExtension::ensureTraversable((isset($context["espaces"]) || array_key_exists("espaces", $context) ? $context["espaces"] : (function () { throw new RuntimeError('Variable "espaces" does not exist.', 37, $this->source); })()));
        $context['_iterated'] = false;
        foreach ($context['_seq'] as $context["_key"] => $context["espace"]) {
            // line 38
            yield "        <div class=\"col-lg-4 col-md-6 align-self-center mb-30 properties-items espace-card\">
          <div class=\"item position-relative\">
            <a href=\"";
            // line 40
            yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape($this->extensions['Symfony\Bridge\Twig\Extension\RoutingExtension']->getPath("app_espace_show", ["idEspace" => CoreExtension::getAttribute($this->env, $this->source, $context["espace"], "idEspace", [], "any", false, false, false, 40)]), "html", null, true);
            yield "\">
              <img 
                src=\"";
            // line 42
            yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape($this->extensions['Symfony\Bridge\Twig\Extension\AssetExtension']->getAssetUrl(("uploads/" . (( !Twig\Extension\CoreExtension::testEmpty(CoreExtension::getAttribute($this->env, $this->source, $context["espace"], "image", [], "any", false, false, false, 42))) ? (CoreExtension::getAttribute($this->env, $this->source, $context["espace"], "image", [], "any", false, false, false, 42)) : ("exemple.jpg")))), "html", null, true);
            yield "\" 
                class=\"card-img-top\" 
                alt=\"";
            // line 44
            yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape(CoreExtension::getAttribute($this->env, $this->source, $context["espace"], "nomEspace", [], "any", false, false, false, 44), "html", null, true);
            yield "\"
              >
              ";
            // line 46
            if (Twig\Extension\CoreExtension::testEmpty(CoreExtension::getAttribute($this->env, $this->source, $context["espace"], "image", [], "any", false, false, false, 46))) {
                // line 47
                yield "                <span class=\"badge badge-secondary position-absolute\"
                      style=\"top: 10px; left: 10px; background-color: rgba(0,0,0,0.7); font-size: 0.75rem;\">
                  Placeholder
                </span>
              ";
            }
            // line 52
            yield "            </a>
            <span class=\"category\">";
            // line 53
            yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape(CoreExtension::getAttribute($this->env, $this->source, $context["espace"], "TypeEspace", [], "any", false, false, false, 53), "html", null, true);
            yield "</span>
            <h6>";
            // line 54
            yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape(CoreExtension::getAttribute($this->env, $this->source, $context["espace"], "prix", [], "any", false, false, false, 54), "html", null, true);
            yield " Dt</h6>
            <h4>
              <a href=\"";
            // line 56
            yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape($this->extensions['Symfony\Bridge\Twig\Extension\RoutingExtension']->getPath("app_espace_show", ["idEspace" => CoreExtension::getAttribute($this->env, $this->source, $context["espace"], "idEspace", [], "any", false, false, false, 56)]), "html", null, true);
            yield "\">
                ";
            // line 57
            yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape(CoreExtension::getAttribute($this->env, $this->source, $context["espace"], "nomEspace", [], "any", false, false, false, 57), "html", null, true);
            yield "
              </a>
            </h4>
            <ul>
              <li>Adresse : <span>";
            // line 61
            yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape(CoreExtension::getAttribute($this->env, $this->source, $context["espace"], "adresse", [], "any", false, false, false, 61), "html", null, true);
            yield "</span></li>
              <li>Capacité : <span>";
            // line 62
            yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape(CoreExtension::getAttribute($this->env, $this->source, $context["espace"], "capacite", [], "any", false, false, false, 62), "html", null, true);
            yield "</span></li>
              <li>Disponibilité : <span>";
            // line 63
            yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape(CoreExtension::getAttribute($this->env, $this->source, $context["espace"], "disponibilite", [], "any", false, false, false, 63), "html", null, true);
            yield "</span></li>
            </ul>
            <div class=\"main-button d-flex justify-content-center\">
              <a href=\"";
            // line 66
            yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape($this->extensions['Symfony\Bridge\Twig\Extension\RoutingExtension']->getPath("app_espace_show", ["idEspace" => CoreExtension::getAttribute($this->env, $this->source, $context["espace"], "idEspace", [], "any", false, false, false, 66)]), "html", null, true);
            yield "\">Détails</a>
            </div>
          </div>
        </div>
      ";
            $context['_iterated'] = true;
        }
        // line 70
        if (!$context['_iterated']) {
            // line 71
            yield "        <div class=\"col-12 text-center\">
          <div class=\"alert alert-warning\">Aucun espace trouvé.</div>
        </div>
      ";
        }
        $_parent = $context['_parent'];
        unset($context['_seq'], $context['_key'], $context['espace'], $context['_parent'], $context['_iterated']);
        $context = array_intersect_key($context, $_parent) + $_parent;
        // line 75
        yield "    </div>
  </div>
</div>

<!-- JS Live Filter -->
<script>
  const searchInput = document.getElementById('searchEspace');
  const cards = document.querySelectorAll('.espace-card');

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
        return array (  229 => 75,  220 => 71,  218 => 70,  209 => 66,  203 => 63,  199 => 62,  195 => 61,  188 => 57,  184 => 56,  179 => 54,  175 => 53,  172 => 52,  165 => 47,  163 => 46,  158 => 44,  153 => 42,  148 => 40,  144 => 38,  139 => 37,  122 => 23,  106 => 10,  100 => 6,  87 => 5,  64 => 3,  41 => 1,);
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

    <!-- Bouton Création -->
    <div class=\"row mb-4\">
      <div class=\"col-lg-12 text-center\">
        <a href=\"{{ path('app_espace_new') }}\" class=\"main-button custom-message-btn\">
          <i></i> + Créer un nouvel espace
        </a>
      </div>
    </div>

    <!-- 🔍 Search Bar -->
    <div class=\"row mb-4\">
      <div class=\"col-lg-12 text-center\">
        <input type=\"text\" id=\"searchEspace\" class=\"form-control w-50 mx-auto\" placeholder=\"Rechercher un espace...\">
      </div>
    </div>

    <div class=\"row properties-box\" id=\"espaceGrid\">
      {% for espace in espaces %}
        <div class=\"col-lg-4 col-md-6 align-self-center mb-30 properties-items espace-card\">
          <div class=\"item position-relative\">
            <a href=\"{{ path('app_espace_show', {'idEspace': espace.idEspace}) }}\">
              <img 
                src=\"{{ asset('uploads/' ~ (espace.image is not empty ? espace.image : 'exemple.jpg')) }}\" 
                class=\"card-img-top\" 
                alt=\"{{ espace.nomEspace }}\"
              >
              {% if espace.image is empty %}
                <span class=\"badge badge-secondary position-absolute\"
                      style=\"top: 10px; left: 10px; background-color: rgba(0,0,0,0.7); font-size: 0.75rem;\">
                  Placeholder
                </span>
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
              <li>Disponibilité : <span>{{ espace.disponibilite }}</span></li>
            </ul>
            <div class=\"main-button d-flex justify-content-center\">
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
  </div>
</div>

<!-- JS Live Filter -->
<script>
  const searchInput = document.getElementById('searchEspace');
  const cards = document.querySelectorAll('.espace-card');

  searchInput.addEventListener('input', function () {
    const query = this.value.toLowerCase();
    cards.forEach(card => {
      const content = card.textContent.toLowerCase();
      card.style.display = content.includes(query) ? '' : 'none';
    });
  });
</script>
{% endblock %}
", "espace/index.html.twig", "C:\\wamp64\\Pi-Javafx-Symfony-selimWeb (8)\\Pi-Javafx-Symfony-selimWeb\\templates\\espace\\index.html.twig");
    }
}
