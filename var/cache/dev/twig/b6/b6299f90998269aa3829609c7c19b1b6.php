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
        // line 19
        yield $this->extensions['Symfony\Bridge\Twig\Extension\RoutingExtension']->getPath("dashboard_espace_new");
        yield "\" class=\"btn btn-success\">
            <i class=\"pe-7s-plus mr-2\"></i> Nouvel Espace
          </a>
        </div>
      </div>
    </div>

    <div class=\"row\">
      ";
        // line 27
        $context['_parent'] = $context;
        $context['_seq'] = CoreExtension::ensureTraversable((isset($context["espaces"]) || array_key_exists("espaces", $context) ? $context["espaces"] : (function () { throw new RuntimeError('Variable "espaces" does not exist.', 27, $this->source); })()));
        $context['_iterated'] = false;
        foreach ($context['_seq'] as $context["_key"] => $context["espace"]) {
            // line 28
            yield "        <div class=\"col-md-6 col-xl-4\">
          <div class=\"card mb-4 shadow-sm\">
            <div class=\"position-relative\">
              <img 
                src=\"";
            // line 32
            yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape($this->extensions['Symfony\Bridge\Twig\Extension\AssetExtension']->getAssetUrl(("uploads/" . (( !Twig\Extension\CoreExtension::testEmpty(CoreExtension::getAttribute($this->env, $this->source, $context["espace"], "image", [], "any", false, false, false, 32))) ? (CoreExtension::getAttribute($this->env, $this->source, $context["espace"], "image", [], "any", false, false, false, 32)) : ("placeholder.jpg")))), "html", null, true);
            yield "\" 
                class=\"card-img-top\" 
                alt=\"";
            // line 34
            yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape(CoreExtension::getAttribute($this->env, $this->source, $context["espace"], "nomEspace", [], "any", false, false, false, 34), "html", null, true);
            yield "\"
              >
              ";
            // line 36
            if (Twig\Extension\CoreExtension::testEmpty(CoreExtension::getAttribute($this->env, $this->source, $context["espace"], "image", [], "any", false, false, false, 36))) {
                // line 37
                yield "                <span class=\"badge badge-secondary position-absolute\"
                      style=\"top: 10px; left: 10px; background-color: rgba(0,0,0,0.7); font-size: 0.75rem;\">
                  Placeholder
                </span>
              ";
            }
            // line 42
            yield "            </div>

            <div class=\"card-body\">
              <h5 class=\"card-title text-uppercase\">";
            // line 45
            yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape(CoreExtension::getAttribute($this->env, $this->source, $context["espace"], "nomEspace", [], "any", false, false, false, 45), "html", null, true);
            yield "</h5>
              <p class=\"card-text text-muted mb-1\">
                <strong>Adresse :</strong> ";
            // line 47
            yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape(CoreExtension::getAttribute($this->env, $this->source, $context["espace"], "adresse", [], "any", false, false, false, 47), "html", null, true);
            yield "<br>
                <strong>Capacité :</strong> ";
            // line 48
            yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape(CoreExtension::getAttribute($this->env, $this->source, $context["espace"], "capacite", [], "any", false, false, false, 48), "html", null, true);
            yield "<br>
                <strong>Type :</strong> ";
            // line 49
            yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape(CoreExtension::getAttribute($this->env, $this->source, $context["espace"], "Type_espace", [], "any", false, false, false, 49), "html", null, true);
            yield "
              </p>
              <p class=\"card-text\">
                <strong>Disponibilité :</strong> ";
            // line 52
            yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape(CoreExtension::getAttribute($this->env, $this->source, $context["espace"], "disponibilite", [], "any", false, false, false, 52), "html", null, true);
            yield "
              </p>
              <div class=\"d-flex justify-content-between align-items-center\">
                <span class=\"badge badge-info p-2\">";
            // line 55
            yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape(CoreExtension::getAttribute($this->env, $this->source, $context["espace"], "prix", [], "any", false, false, false, 55), "html", null, true);
            yield " DT</span>
                <a href=\"";
            // line 56
            yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape($this->extensions['Symfony\Bridge\Twig\Extension\RoutingExtension']->getPath("dashboard_espace_show", ["idEspace" => CoreExtension::getAttribute($this->env, $this->source, $context["espace"], "idEspace", [], "any", false, false, false, 56)]), "html", null, true);
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
        // line 63
        if (!$context['_iterated']) {
            // line 64
            yield "        <div class=\"col-12\">
          <div class=\"alert alert-warning text-center\">Aucun espace trouvé.</div>
        </div>
      ";
        }
        $_parent = $context['_parent'];
        unset($context['_seq'], $context['_key'], $context['espace'], $context['_parent'], $context['_iterated']);
        $context = array_intersect_key($context, $_parent) + $_parent;
        // line 68
        yield "    </div>
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
        return array (  213 => 68,  204 => 64,  202 => 63,  190 => 56,  186 => 55,  180 => 52,  174 => 49,  170 => 48,  166 => 47,  161 => 45,  156 => 42,  149 => 37,  147 => 36,  142 => 34,  137 => 32,  131 => 28,  126 => 27,  115 => 19,  100 => 6,  87 => 5,  64 => 3,  41 => 1,);
    }

    public function getSourceContext(): Source
    {
        return new Source("{% extends 'baseBack.html.twig' %}

{% block title %}Espaces{% endblock %}

{% block body %}
<div class=\"app-main__outer\">
  <div class=\"app-main__inner\">
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

    <div class=\"row\">
      {% for espace in espaces %}
        <div class=\"col-md-6 col-xl-4\">
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
                <strong>Adresse :</strong> {{ espace.adresse }}<br>
                <strong>Capacité :</strong> {{ espace.capacite }}<br>
                <strong>Type :</strong> {{ espace.Type_espace }}
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
{% endblock %}
", "espace/indexBack.html.twig", "C:\\wamp64\\gestion_espace_symfony\\templates\\espace\\indexBack.html.twig");
    }
}
