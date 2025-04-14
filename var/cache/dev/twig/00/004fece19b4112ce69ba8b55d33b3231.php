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

/* baseBack.html.twig */
class __TwigTemplate_65f00601a5ab56aed3f3fd0da92e0efd extends Template
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

        $this->parent = false;

        $this->blocks = [
            'body' => [$this, 'block_body'],
        ];
    }

    protected function doDisplay(array $context, array $blocks = []): iterable
    {
        $macros = $this->macros;
        $__internal_5a27a8ba21ca79b61932376b2fa922d2 = $this->extensions["Symfony\\Bundle\\WebProfilerBundle\\Twig\\WebProfilerExtension"];
        $__internal_5a27a8ba21ca79b61932376b2fa922d2->enter($__internal_5a27a8ba21ca79b61932376b2fa922d2_prof = new \Twig\Profiler\Profile($this->getTemplateName(), "template", "baseBack.html.twig"));

        $__internal_6f47bbe9983af81f1e7450e9a3e3768f = $this->extensions["Symfony\\Bridge\\Twig\\Extension\\ProfilerExtension"];
        $__internal_6f47bbe9983af81f1e7450e9a3e3768f->enter($__internal_6f47bbe9983af81f1e7450e9a3e3768f_prof = new \Twig\Profiler\Profile($this->getTemplateName(), "template", "baseBack.html.twig"));

        // line 1
        yield "<!doctype html>
<html lang=\"en\">

";
        // line 4
        yield from $this->loadTemplate("partialsBack/headBack.html.twig", "baseBack.html.twig", 4)->unwrap()->yield($context);
        // line 5
        yield "
<body>
    <div class=\"app-container app-theme-white body-tabs-shadow fixed-sidebar fixed-header\">
        ";
        // line 8
        yield from $this->loadTemplate("partialsBack/navbar.html.twig", "baseBack.html.twig", 8)->unwrap()->yield($context);
        // line 9
        yield "        <div class=\"app-main\">
            ";
        // line 10
        yield from $this->loadTemplate("partialsBack/sidebar.html.twig", "baseBack.html.twig", 10)->unwrap()->yield($context);
        // line 11
        yield "            ";
        yield from $this->unwrap()->yieldBlock('body', $context, $blocks);
        // line 16
        yield "        </div>
    </div>
    <script type=\"text/javascript\" src=\"";
        // line 18
        yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape($this->extensions['Symfony\Bridge\Twig\Extension\AssetExtension']->getAssetUrl("assets/scripts/main.js"), "html", null, true);
        yield "\"></script>
</body>

</html>";
        
        $__internal_5a27a8ba21ca79b61932376b2fa922d2->leave($__internal_5a27a8ba21ca79b61932376b2fa922d2_prof);

        
        $__internal_6f47bbe9983af81f1e7450e9a3e3768f->leave($__internal_6f47bbe9983af81f1e7450e9a3e3768f_prof);

        yield from [];
    }

    // line 11
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

        // line 12
        yield "            ";
        yield from $this->loadTemplate("partialsBack/mainBody.html.twig", "baseBack.html.twig", 12)->unwrap()->yield($context);
        // line 13
        yield "            
            <script src=\"http://maps.google.com/maps/api/js?sensor=true\"></script>
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
        return "baseBack.html.twig";
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
        return array (  106 => 13,  103 => 12,  90 => 11,  75 => 18,  71 => 16,  68 => 11,  66 => 10,  63 => 9,  61 => 8,  56 => 5,  54 => 4,  49 => 1,);
    }

    public function getSourceContext(): Source
    {
        return new Source("<!doctype html>
<html lang=\"en\">

{% include 'partialsBack/headBack.html.twig'%}

<body>
    <div class=\"app-container app-theme-white body-tabs-shadow fixed-sidebar fixed-header\">
        {% include 'partialsBack/navbar.html.twig' %}
        <div class=\"app-main\">
            {% include 'partialsBack/sidebar.html.twig'%}
            {% block body %}
            {% include 'partialsBack/mainBody.html.twig'%}
            
            <script src=\"http://maps.google.com/maps/api/js?sensor=true\"></script>
            {% endblock %}
        </div>
    </div>
    <script type=\"text/javascript\" src=\"{{ asset('assets/scripts/main.js') }}\"></script>
</body>

</html>", "baseBack.html.twig", "C:\\wamp64\\gestion_espace_symfony\\templates\\baseBack.html.twig");
    }
}
