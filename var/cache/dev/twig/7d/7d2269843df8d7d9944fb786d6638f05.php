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

/* event/show.html.twig */
class __TwigTemplate_a0d2d2a399de3eadd5f2779bd8090e48 extends Template
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
        $__internal_5a27a8ba21ca79b61932376b2fa922d2->enter($__internal_5a27a8ba21ca79b61932376b2fa922d2_prof = new \Twig\Profiler\Profile($this->getTemplateName(), "template", "event/show.html.twig"));

        $__internal_6f47bbe9983af81f1e7450e9a3e3768f = $this->extensions["Symfony\\Bridge\\Twig\\Extension\\ProfilerExtension"];
        $__internal_6f47bbe9983af81f1e7450e9a3e3768f->enter($__internal_6f47bbe9983af81f1e7450e9a3e3768f_prof = new \Twig\Profiler\Profile($this->getTemplateName(), "template", "event/show.html.twig"));

        $this->parent = $this->loadTemplate("base.html.twig", "event/show.html.twig", 1);
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
        yield "<div class=\"section best-deal animate__animated animate__fadeIn\">
  <div class=\"container\">
    <div class=\"row\">
      <!-- Titre -->
      <div class=\"col-lg-4\">
        <div class=\"section-heading animate__animated animate__fadeInLeft\">
          <h6>| Évènement</h6>
          <h2>";
        // line 13
        yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape(CoreExtension::getAttribute($this->env, $this->source, (isset($context["event"]) || array_key_exists("event", $context) ? $context["event"] : (function () { throw new RuntimeError('Variable "event" does not exist.', 13, $this->source); })()), "nomEvent", [], "any", false, false, false, 13), "html", null, true);
        yield "</h2>
        </div>
      </div>

      <!-- Détails & Image côte à côte -->
      <div class=\"col-lg-12 mt-4\">
        <div class=\"row align-items-center\">
          <!-- Détails -->
          <div class=\"col-lg-6 animate__animated animate__fadeInLeft\">
            <div class=\"info-table\">
              <ul>
                <li>Prix <span>";
        // line 24
        yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape(CoreExtension::getAttribute($this->env, $this->source, (isset($context["event"]) || array_key_exists("event", $context) ? $context["event"] : (function () { throw new RuntimeError('Variable "event" does not exist.', 24, $this->source); })()), "prix", [], "any", false, false, false, 24), "html", null, true);
        yield " DT</span></li>
                <li>Date <span>";
        // line 25
        yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape(CoreExtension::getAttribute($this->env, $this->source, (isset($context["event"]) || array_key_exists("event", $context) ? $context["event"] : (function () { throw new RuntimeError('Variable "event" does not exist.', 25, $this->source); })()), "date", [], "any", false, false, false, 25), "html", null, true);
        yield "</span></li>
                <li>Visiteurs <span>";
        // line 26
        yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape(CoreExtension::getAttribute($this->env, $this->source, (isset($context["event"]) || array_key_exists("event", $context) ? $context["event"] : (function () { throw new RuntimeError('Variable "event" does not exist.', 26, $this->source); })()), "nbrVisiteurs", [], "any", false, false, false, 26), "html", null, true);
        yield "</span></li>
                <li>Espace <span>";
        // line 27
        yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape(CoreExtension::getAttribute($this->env, $this->source, (isset($context["event"]) || array_key_exists("event", $context) ? $context["event"] : (function () { throw new RuntimeError('Variable "event" does not exist.', 27, $this->source); })()), "nomEspace", [], "any", false, false, false, 27), "html", null, true);
        yield "</span></li>
              </ul>
              <h5 class=\"mt-4\">Description</h5>
              <p>";
        // line 30
        yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape(CoreExtension::getAttribute($this->env, $this->source, (isset($context["event"]) || array_key_exists("event", $context) ? $context["event"] : (function () { throw new RuntimeError('Variable "event" does not exist.', 30, $this->source); })()), "details", [], "any", false, false, false, 30), "html", null, true);
        yield "</p>

              <a href=\"";
        // line 32
        yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape($this->extensions['Symfony\Bridge\Twig\Extension\RoutingExtension']->getPath("app_event_edit", ["idEvent" => CoreExtension::getAttribute($this->env, $this->source, (isset($context["event"]) || array_key_exists("event", $context) ? $context["event"] : (function () { throw new RuntimeError('Variable "event" does not exist.', 32, $this->source); })()), "idEvent", [], "any", false, false, false, 32)]), "html", null, true);
        yield "\" class=\"btn btn-primary mt-3 me-2\">
                <i class=\"fa fa-pen\"></i> Modifier
              </a>
              ";
        // line 35
        yield Twig\Extension\CoreExtension::include($this->env, $context, "event/_delete_form.html.twig");
        yield "
              <a href=\"";
        // line 36
        yield $this->extensions['Symfony\Bridge\Twig\Extension\RoutingExtension']->getPath("app_event_index");
        yield "\" class=\"btn btn-outline-secondary mt-3\">
                <i class=\"fa fa-arrow-left\"></i> Retour à la liste
              </a>
            </div>
          </div>

          <!-- Image -->
          <div class=\"col-lg-6 text-center animate__animated animate__zoomIn\">
            <img src=\"";
        // line 44
        yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape($this->extensions['Symfony\Bridge\Twig\Extension\AssetExtension']->getAssetUrl(("uploads/" . CoreExtension::getAttribute($this->env, $this->source, (isset($context["event"]) || array_key_exists("event", $context) ? $context["event"] : (function () { throw new RuntimeError('Variable "event" does not exist.', 44, $this->source); })()), "image", [], "any", false, false, false, 44))), "html", null, true);
        yield "\" class=\"img-fluid rounded shadow\" alt=\"";
        yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape(CoreExtension::getAttribute($this->env, $this->source, (isset($context["event"]) || array_key_exists("event", $context) ? $context["event"] : (function () { throw new RuntimeError('Variable "event" does not exist.', 44, $this->source); })()), "nomEvent", [], "any", false, false, false, 44), "html", null, true);
        yield "\" style=\"max-height: 350px;\">
          </div>
        </div>
      </div>
    </div>
  </div>
</div>

<!-- Footer -->
<footer class=\"footer-no-gap mt-5\">
  <div class=\"container\">
    <div class=\"col-lg-12 text-center\">
      <p>
        Copyright © 2048 Villa Agency Co., Ltd.
        Design:
        <a rel=\"nofollow\" href=\"https://templatemo.com\" target=\"_blank\">TemplateMo</a>
      </p>
    </div>
  </div>
</footer>
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
        return "event/show.html.twig";
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
        return array (  167 => 44,  156 => 36,  152 => 35,  146 => 32,  141 => 30,  135 => 27,  131 => 26,  127 => 25,  123 => 24,  109 => 13,  100 => 6,  87 => 5,  64 => 3,  41 => 1,);
    }

    public function getSourceContext(): Source
    {
        return new Source("{% extends 'base.html.twig' %}

{% block title %}Détails de l'Évènement{% endblock %}

{% block body %}
<div class=\"section best-deal animate__animated animate__fadeIn\">
  <div class=\"container\">
    <div class=\"row\">
      <!-- Titre -->
      <div class=\"col-lg-4\">
        <div class=\"section-heading animate__animated animate__fadeInLeft\">
          <h6>| Évènement</h6>
          <h2>{{ event.nomEvent }}</h2>
        </div>
      </div>

      <!-- Détails & Image côte à côte -->
      <div class=\"col-lg-12 mt-4\">
        <div class=\"row align-items-center\">
          <!-- Détails -->
          <div class=\"col-lg-6 animate__animated animate__fadeInLeft\">
            <div class=\"info-table\">
              <ul>
                <li>Prix <span>{{ event.prix }} DT</span></li>
                <li>Date <span>{{ event.date }}</span></li>
                <li>Visiteurs <span>{{ event.nbrVisiteurs }}</span></li>
                <li>Espace <span>{{ event.nomEspace }}</span></li>
              </ul>
              <h5 class=\"mt-4\">Description</h5>
              <p>{{ event.details }}</p>

              <a href=\"{{ path('app_event_edit', {'idEvent': event.idEvent}) }}\" class=\"btn btn-primary mt-3 me-2\">
                <i class=\"fa fa-pen\"></i> Modifier
              </a>
              {{ include('event/_delete_form.html.twig') }}
              <a href=\"{{ path('app_event_index') }}\" class=\"btn btn-outline-secondary mt-3\">
                <i class=\"fa fa-arrow-left\"></i> Retour à la liste
              </a>
            </div>
          </div>

          <!-- Image -->
          <div class=\"col-lg-6 text-center animate__animated animate__zoomIn\">
            <img src=\"{{ asset('uploads/' ~ event.image) }}\" class=\"img-fluid rounded shadow\" alt=\"{{ event.nomEvent }}\" style=\"max-height: 350px;\">
          </div>
        </div>
      </div>
    </div>
  </div>
</div>

<!-- Footer -->
<footer class=\"footer-no-gap mt-5\">
  <div class=\"container\">
    <div class=\"col-lg-12 text-center\">
      <p>
        Copyright © 2048 Villa Agency Co., Ltd.
        Design:
        <a rel=\"nofollow\" href=\"https://templatemo.com\" target=\"_blank\">TemplateMo</a>
      </p>
    </div>
  </div>
</footer>
{% endblock %}
", "event/show.html.twig", "C:\\wamp64\\gestion_espace_symfony\\templates\\event\\show.html.twig");
    }
}
