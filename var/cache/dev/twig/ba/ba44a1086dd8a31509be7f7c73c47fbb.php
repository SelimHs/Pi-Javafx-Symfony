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

/* espace/edit.html.twig */
class __TwigTemplate_6b2e1708cf7683c332660a670dd33a37 extends Template
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
        $__internal_5a27a8ba21ca79b61932376b2fa922d2->enter($__internal_5a27a8ba21ca79b61932376b2fa922d2_prof = new \Twig\Profiler\Profile($this->getTemplateName(), "template", "espace/edit.html.twig"));

        $__internal_6f47bbe9983af81f1e7450e9a3e3768f = $this->extensions["Symfony\\Bridge\\Twig\\Extension\\ProfilerExtension"];
        $__internal_6f47bbe9983af81f1e7450e9a3e3768f->enter($__internal_6f47bbe9983af81f1e7450e9a3e3768f_prof = new \Twig\Profiler\Profile($this->getTemplateName(), "template", "espace/edit.html.twig"));

        $this->parent = $this->loadTemplate("base.html.twig", "espace/edit.html.twig", 1);
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

        yield "Modifier Espace";
        
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
        yield "<div class=\"container mt-5\">
  <div class=\"row justify-content-center\">
    <div class=\"col-lg-8\">
      <div class=\"card shadow-lg border-0 rounded-4\">
        <div class=\"card-body p-5\">
          <h2 class=\"mb-4 text-center\">Modifier l'Espace</h2>

          ";
        // line 13
        yield         $this->env->getRuntime('Symfony\Component\Form\FormRenderer')->renderBlock((isset($context["form"]) || array_key_exists("form", $context) ? $context["form"] : (function () { throw new RuntimeError('Variable "form" does not exist.', 13, $this->source); })()), 'form_start', ["attr" => ["novalidate" => "novalidate"]]);
        yield "

          <div class=\"mb-3\">
            ";
        // line 16
        yield $this->env->getRuntime('Symfony\Component\Form\FormRenderer')->searchAndRenderBlock(CoreExtension::getAttribute($this->env, $this->source, (isset($context["form"]) || array_key_exists("form", $context) ? $context["form"] : (function () { throw new RuntimeError('Variable "form" does not exist.', 16, $this->source); })()), "nomEspace", [], "any", false, false, false, 16), 'label');
        yield "
            ";
        // line 17
        yield $this->env->getRuntime('Symfony\Component\Form\FormRenderer')->searchAndRenderBlock(CoreExtension::getAttribute($this->env, $this->source, (isset($context["form"]) || array_key_exists("form", $context) ? $context["form"] : (function () { throw new RuntimeError('Variable "form" does not exist.', 17, $this->source); })()), "nomEspace", [], "any", false, false, false, 17), 'widget', ["attr" => ["class" => "form-control", "required" => "required", "minlength" => "4"]]);
        // line 19
        yield "
            <div class=\"text-danger small mt-1\">";
        // line 20
        yield $this->env->getRuntime('Symfony\Component\Form\FormRenderer')->searchAndRenderBlock(CoreExtension::getAttribute($this->env, $this->source, (isset($context["form"]) || array_key_exists("form", $context) ? $context["form"] : (function () { throw new RuntimeError('Variable "form" does not exist.', 20, $this->source); })()), "nomEspace", [], "any", false, false, false, 20), 'errors');
        yield "</div>
          </div>

          <div class=\"mb-3\">
            ";
        // line 24
        yield $this->env->getRuntime('Symfony\Component\Form\FormRenderer')->searchAndRenderBlock(CoreExtension::getAttribute($this->env, $this->source, (isset($context["form"]) || array_key_exists("form", $context) ? $context["form"] : (function () { throw new RuntimeError('Variable "form" does not exist.', 24, $this->source); })()), "adresse", [], "any", false, false, false, 24), 'label');
        yield "
            ";
        // line 25
        yield $this->env->getRuntime('Symfony\Component\Form\FormRenderer')->searchAndRenderBlock(CoreExtension::getAttribute($this->env, $this->source, (isset($context["form"]) || array_key_exists("form", $context) ? $context["form"] : (function () { throw new RuntimeError('Variable "form" does not exist.', 25, $this->source); })()), "adresse", [], "any", false, false, false, 25), 'widget', ["attr" => ["class" => "form-control", "required" => "required", "minlength" => "4"]]);
        // line 27
        yield "
            <div class=\"text-danger small mt-1\">";
        // line 28
        yield $this->env->getRuntime('Symfony\Component\Form\FormRenderer')->searchAndRenderBlock(CoreExtension::getAttribute($this->env, $this->source, (isset($context["form"]) || array_key_exists("form", $context) ? $context["form"] : (function () { throw new RuntimeError('Variable "form" does not exist.', 28, $this->source); })()), "adresse", [], "any", false, false, false, 28), 'errors');
        yield "</div>
          </div>

          <div class=\"mb-3\">
            ";
        // line 32
        yield $this->env->getRuntime('Symfony\Component\Form\FormRenderer')->searchAndRenderBlock(CoreExtension::getAttribute($this->env, $this->source, (isset($context["form"]) || array_key_exists("form", $context) ? $context["form"] : (function () { throw new RuntimeError('Variable "form" does not exist.', 32, $this->source); })()), "capacite", [], "any", false, false, false, 32), 'label');
        yield "
            ";
        // line 33
        yield $this->env->getRuntime('Symfony\Component\Form\FormRenderer')->searchAndRenderBlock(CoreExtension::getAttribute($this->env, $this->source, (isset($context["form"]) || array_key_exists("form", $context) ? $context["form"] : (function () { throw new RuntimeError('Variable "form" does not exist.', 33, $this->source); })()), "capacite", [], "any", false, false, false, 33), 'widget', ["attr" => ["class" => "form-control", "required" => "required", "type" => "number", "min" => "50"]]);
        // line 35
        yield "
            <div class=\"text-danger small mt-1\">";
        // line 36
        yield $this->env->getRuntime('Symfony\Component\Form\FormRenderer')->searchAndRenderBlock(CoreExtension::getAttribute($this->env, $this->source, (isset($context["form"]) || array_key_exists("form", $context) ? $context["form"] : (function () { throw new RuntimeError('Variable "form" does not exist.', 36, $this->source); })()), "capacite", [], "any", false, false, false, 36), 'errors');
        yield "</div>
          </div>

          <div class=\"mb-3\">
            ";
        // line 40
        yield $this->env->getRuntime('Symfony\Component\Form\FormRenderer')->searchAndRenderBlock(CoreExtension::getAttribute($this->env, $this->source, (isset($context["form"]) || array_key_exists("form", $context) ? $context["form"] : (function () { throw new RuntimeError('Variable "form" does not exist.', 40, $this->source); })()), "disponibilite", [], "any", false, false, false, 40), 'label');
        yield "
            ";
        // line 41
        yield $this->env->getRuntime('Symfony\Component\Form\FormRenderer')->searchAndRenderBlock(CoreExtension::getAttribute($this->env, $this->source, (isset($context["form"]) || array_key_exists("form", $context) ? $context["form"] : (function () { throw new RuntimeError('Variable "form" does not exist.', 41, $this->source); })()), "disponibilite", [], "any", false, false, false, 41), 'widget', ["attr" => ["class" => "form-control", "readonly" => "readonly"]]);
        // line 43
        yield "
            <div class=\"text-danger small mt-1\">";
        // line 44
        yield $this->env->getRuntime('Symfony\Component\Form\FormRenderer')->searchAndRenderBlock(CoreExtension::getAttribute($this->env, $this->source, (isset($context["form"]) || array_key_exists("form", $context) ? $context["form"] : (function () { throw new RuntimeError('Variable "form" does not exist.', 44, $this->source); })()), "disponibilite", [], "any", false, false, false, 44), 'errors');
        yield "</div>
          </div>

          <div class=\"mb-3\">
            ";
        // line 48
        yield $this->env->getRuntime('Symfony\Component\Form\FormRenderer')->searchAndRenderBlock(CoreExtension::getAttribute($this->env, $this->source, (isset($context["form"]) || array_key_exists("form", $context) ? $context["form"] : (function () { throw new RuntimeError('Variable "form" does not exist.', 48, $this->source); })()), "prix", [], "any", false, false, false, 48), 'label');
        yield "
            ";
        // line 49
        yield $this->env->getRuntime('Symfony\Component\Form\FormRenderer')->searchAndRenderBlock(CoreExtension::getAttribute($this->env, $this->source, (isset($context["form"]) || array_key_exists("form", $context) ? $context["form"] : (function () { throw new RuntimeError('Variable "form" does not exist.', 49, $this->source); })()), "prix", [], "any", false, false, false, 49), 'widget', ["attr" => ["class" => "form-control", "required" => "required", "type" => "number"]]);
        // line 51
        yield "
            <div class=\"text-danger small mt-1\">";
        // line 52
        yield $this->env->getRuntime('Symfony\Component\Form\FormRenderer')->searchAndRenderBlock(CoreExtension::getAttribute($this->env, $this->source, (isset($context["form"]) || array_key_exists("form", $context) ? $context["form"] : (function () { throw new RuntimeError('Variable "form" does not exist.', 52, $this->source); })()), "prix", [], "any", false, false, false, 52), 'errors');
        yield "</div>
          </div>

          <div class=\"mb-3\">
            ";
        // line 56
        yield $this->env->getRuntime('Symfony\Component\Form\FormRenderer')->searchAndRenderBlock(CoreExtension::getAttribute($this->env, $this->source, (isset($context["form"]) || array_key_exists("form", $context) ? $context["form"] : (function () { throw new RuntimeError('Variable "form" does not exist.', 56, $this->source); })()), "Type_espace", [], "any", false, false, false, 56), 'label');
        yield "
            ";
        // line 57
        yield $this->env->getRuntime('Symfony\Component\Form\FormRenderer')->searchAndRenderBlock(CoreExtension::getAttribute($this->env, $this->source, (isset($context["form"]) || array_key_exists("form", $context) ? $context["form"] : (function () { throw new RuntimeError('Variable "form" does not exist.', 57, $this->source); })()), "Type_espace", [], "any", false, false, false, 57), 'widget', ["attr" => ["class" => "form-control", "required" => "required"]]);
        // line 59
        yield "
            <div class=\"text-danger small mt-1\">";
        // line 60
        yield $this->env->getRuntime('Symfony\Component\Form\FormRenderer')->searchAndRenderBlock(CoreExtension::getAttribute($this->env, $this->source, (isset($context["form"]) || array_key_exists("form", $context) ? $context["form"] : (function () { throw new RuntimeError('Variable "form" does not exist.', 60, $this->source); })()), "Type_espace", [], "any", false, false, false, 60), 'errors');
        yield "</div>
          </div>

          ";
        // line 64
        yield "          ";
        if (CoreExtension::getAttribute($this->env, $this->source, (isset($context["espace"]) || array_key_exists("espace", $context) ? $context["espace"] : (function () { throw new RuntimeError('Variable "espace" does not exist.', 64, $this->source); })()), "image", [], "any", false, false, false, 64)) {
            // line 65
            yield "            <div class=\"mb-3 text-center\">
              <img src=\"";
            // line 66
            yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape($this->extensions['Symfony\Bridge\Twig\Extension\AssetExtension']->getAssetUrl(("uploads/" . CoreExtension::getAttribute($this->env, $this->source, (isset($context["espace"]) || array_key_exists("espace", $context) ? $context["espace"] : (function () { throw new RuntimeError('Variable "espace" does not exist.', 66, $this->source); })()), "image", [], "any", false, false, false, 66))), "html", null, true);
            yield "\" alt=\"Image actuelle\" class=\"img-thumbnail\" style=\"max-height: 200px;\">
              <p class=\"text-muted mt-2\">Image actuelle</p>
            </div>
          ";
        }
        // line 70
        yield "
          <div class=\"mb-3\">
            ";
        // line 72
        yield $this->env->getRuntime('Symfony\Component\Form\FormRenderer')->searchAndRenderBlock(CoreExtension::getAttribute($this->env, $this->source, (isset($context["form"]) || array_key_exists("form", $context) ? $context["form"] : (function () { throw new RuntimeError('Variable "form" does not exist.', 72, $this->source); })()), "image", [], "any", false, false, false, 72), 'label');
        yield "
            ";
        // line 73
        yield $this->env->getRuntime('Symfony\Component\Form\FormRenderer')->searchAndRenderBlock(CoreExtension::getAttribute($this->env, $this->source, (isset($context["form"]) || array_key_exists("form", $context) ? $context["form"] : (function () { throw new RuntimeError('Variable "form" does not exist.', 73, $this->source); })()), "image", [], "any", false, false, false, 73), 'widget', ["attr" => ["class" => "form-control"]]);
        // line 75
        yield "
            <div class=\"text-danger small mt-1\">";
        // line 76
        yield $this->env->getRuntime('Symfony\Component\Form\FormRenderer')->searchAndRenderBlock(CoreExtension::getAttribute($this->env, $this->source, (isset($context["form"]) || array_key_exists("form", $context) ? $context["form"] : (function () { throw new RuntimeError('Variable "form" does not exist.', 76, $this->source); })()), "image", [], "any", false, false, false, 76), 'errors');
        yield "</div>
          </div>

          <div class=\"text-center\">
            <button class=\"btn btn-warning px-4\">";
        // line 80
        yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape(((array_key_exists("button_label", $context)) ? (Twig\Extension\CoreExtension::default((isset($context["button_label"]) || array_key_exists("button_label", $context) ? $context["button_label"] : (function () { throw new RuntimeError('Variable "button_label" does not exist.', 80, $this->source); })()), "Update")) : ("Update")), "html", null, true);
        yield "</button>
          </div>

          ";
        // line 83
        yield         $this->env->getRuntime('Symfony\Component\Form\FormRenderer')->renderBlock((isset($context["form"]) || array_key_exists("form", $context) ? $context["form"] : (function () { throw new RuntimeError('Variable "form" does not exist.', 83, $this->source); })()), 'form_end');
        yield "

          <div class=\"text-center mt-3\">
            <a href=\"";
        // line 86
        yield $this->extensions['Symfony\Bridge\Twig\Extension\RoutingExtension']->getPath("app_espace_index");
        yield "\" class=\"btn btn-outline-secondary\">← Retour à la liste</a>
          </div>

          <div class=\"text-center mt-3\">
            ";
        // line 90
        yield Twig\Extension\CoreExtension::include($this->env, $context, "espace/_delete_form.html.twig");
        yield "
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
        return "espace/edit.html.twig";
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
        return array (  262 => 90,  255 => 86,  249 => 83,  243 => 80,  236 => 76,  233 => 75,  231 => 73,  227 => 72,  223 => 70,  216 => 66,  213 => 65,  210 => 64,  204 => 60,  201 => 59,  199 => 57,  195 => 56,  188 => 52,  185 => 51,  183 => 49,  179 => 48,  172 => 44,  169 => 43,  167 => 41,  163 => 40,  156 => 36,  153 => 35,  151 => 33,  147 => 32,  140 => 28,  137 => 27,  135 => 25,  131 => 24,  124 => 20,  121 => 19,  119 => 17,  115 => 16,  109 => 13,  100 => 6,  87 => 5,  64 => 3,  41 => 1,);
    }

    public function getSourceContext(): Source
    {
        return new Source("{% extends 'base.html.twig' %}

{% block title %}Modifier Espace{% endblock %}

{% block body %}
<div class=\"container mt-5\">
  <div class=\"row justify-content-center\">
    <div class=\"col-lg-8\">
      <div class=\"card shadow-lg border-0 rounded-4\">
        <div class=\"card-body p-5\">
          <h2 class=\"mb-4 text-center\">Modifier l'Espace</h2>

          {{ form_start(form, {'attr': {'novalidate': 'novalidate'}}) }}

          <div class=\"mb-3\">
            {{ form_label(form.nomEspace) }}
            {{ form_widget(form.nomEspace, {
              'attr': {'class': 'form-control', 'required': 'required', 'minlength': '4'}
            }) }}
            <div class=\"text-danger small mt-1\">{{ form_errors(form.nomEspace) }}</div>
          </div>

          <div class=\"mb-3\">
            {{ form_label(form.adresse) }}
            {{ form_widget(form.adresse, {
              'attr': {'class': 'form-control', 'required': 'required', 'minlength': '4'}
            }) }}
            <div class=\"text-danger small mt-1\">{{ form_errors(form.adresse) }}</div>
          </div>

          <div class=\"mb-3\">
            {{ form_label(form.capacite) }}
            {{ form_widget(form.capacite, {
              'attr': {'class': 'form-control', 'required': 'required', 'type': 'number', 'min': '50'}
            }) }}
            <div class=\"text-danger small mt-1\">{{ form_errors(form.capacite) }}</div>
          </div>

          <div class=\"mb-3\">
            {{ form_label(form.disponibilite) }}
            {{ form_widget(form.disponibilite, {
              'attr': {'class': 'form-control', 'readonly': 'readonly'}
            }) }}
            <div class=\"text-danger small mt-1\">{{ form_errors(form.disponibilite) }}</div>
          </div>

          <div class=\"mb-3\">
            {{ form_label(form.prix) }}
            {{ form_widget(form.prix, {
              'attr': {'class': 'form-control', 'required': 'required', 'type': 'number'}
            }) }}
            <div class=\"text-danger small mt-1\">{{ form_errors(form.prix) }}</div>
          </div>

          <div class=\"mb-3\">
            {{ form_label(form.Type_espace) }}
            {{ form_widget(form.Type_espace, {
              'attr': {'class': 'form-control', 'required': 'required'}
            }) }}
            <div class=\"text-danger small mt-1\">{{ form_errors(form.Type_espace) }}</div>
          </div>

          {# ✅ Affichage de l’image actuelle #}
          {% if espace.image %}
            <div class=\"mb-3 text-center\">
              <img src=\"{{ asset('uploads/' ~ espace.image) }}\" alt=\"Image actuelle\" class=\"img-thumbnail\" style=\"max-height: 200px;\">
              <p class=\"text-muted mt-2\">Image actuelle</p>
            </div>
          {% endif %}

          <div class=\"mb-3\">
            {{ form_label(form.image) }}
            {{ form_widget(form.image, {
              'attr': {'class': 'form-control'}
            }) }}
            <div class=\"text-danger small mt-1\">{{ form_errors(form.image) }}</div>
          </div>

          <div class=\"text-center\">
            <button class=\"btn btn-warning px-4\">{{ button_label|default('Update') }}</button>
          </div>

          {{ form_end(form) }}

          <div class=\"text-center mt-3\">
            <a href=\"{{ path('app_espace_index') }}\" class=\"btn btn-outline-secondary\">← Retour à la liste</a>
          </div>

          <div class=\"text-center mt-3\">
            {{ include('espace/_delete_form.html.twig') }}
          </div>
        </div>
      </div>
    </div>
  </div>
</div>
{% endblock %}
", "espace/edit.html.twig", "C:\\wamp64\\Pi-Javafx-Symfony-selimWeb (8)\\Pi-Javafx-Symfony-selimWeb\\templates\\espace\\edit.html.twig");
    }
}
