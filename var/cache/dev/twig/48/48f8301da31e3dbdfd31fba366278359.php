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

/* partialsBack/sidebar.html.twig */
class __TwigTemplate_3cb9f18540e7d8d8e38696f6e430d556 extends Template
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
        ];
    }

    protected function doDisplay(array $context, array $blocks = []): iterable
    {
        $macros = $this->macros;
        $__internal_5a27a8ba21ca79b61932376b2fa922d2 = $this->extensions["Symfony\\Bundle\\WebProfilerBundle\\Twig\\WebProfilerExtension"];
        $__internal_5a27a8ba21ca79b61932376b2fa922d2->enter($__internal_5a27a8ba21ca79b61932376b2fa922d2_prof = new \Twig\Profiler\Profile($this->getTemplateName(), "template", "partialsBack/sidebar.html.twig"));

        $__internal_6f47bbe9983af81f1e7450e9a3e3768f = $this->extensions["Symfony\\Bridge\\Twig\\Extension\\ProfilerExtension"];
        $__internal_6f47bbe9983af81f1e7450e9a3e3768f->enter($__internal_6f47bbe9983af81f1e7450e9a3e3768f_prof = new \Twig\Profiler\Profile($this->getTemplateName(), "template", "partialsBack/sidebar.html.twig"));

        // line 1
        yield "<div class=\"app-sidebar sidebar-shadow\">
    <div class=\"app-header__logo\">
        <div class=\"logo-src\"></div>
        <div class=\"header__pane ml-auto\">
            <div>
                <button type=\"button\" class=\"hamburger close-sidebar-btn hamburger--elastic\"
                    data-class=\"closed-sidebar\">
                    <span class=\"hamburger-box\">
                        <span class=\"hamburger-inner\"></span>
                    </span>
                </button>
            </div>
        </div>
    </div>
    <div class=\"app-header__mobile-menu\">
        <div>
            <button type=\"button\" class=\"hamburger hamburger--elastic mobile-toggle-nav\">
                <span class=\"hamburger-box\">
                    <span class=\"hamburger-inner\"></span>
                </span>
            </button>
        </div>
    </div>
    <div class=\"app-header__menu\">
        <span>
            <button type=\"button\"
                class=\"btn-icon btn-icon-only btn btn-primary btn-sm mobile-toggle-header-nav\">
                <span class=\"btn-icon-wrapper\">
                    <i class=\"fa fa-ellipsis-v fa-w-6\"></i>
                </span>
            </button>
        </span>
    </div>
    <div class=\"scrollbar-sidebar\">
        <div class=\"app-sidebar__inner\">
            <ul class=\"vertical-nav-menu\">
                <li class=\"app-sidebar__heading\">Dashboards</li>
                <li>
                    <a href=\"";
        // line 39
        yield $this->extensions['Symfony\Bridge\Twig\Extension\RoutingExtension']->getPath("app_dashboard");
        yield "\" class=\"mm-active\">
                        <i class=\"metismenu-icon pe-7s-rocket\"></i>
                        Dashboard Example 1
                    </a>
                </li>
            
                
                <li class=\"app-sidebar__heading\">Évènements</li>
                <li>
                <a href=\"#\">
                    <i class=\"metismenu-icon pe-7s-date\"></i>
                    Gestion Évènements
                    <i class=\"metismenu-state-icon pe-7s-angle-down caret-left\"></i>
                </a>
                <ul>
                    
                    <li>
                    <a href=\"";
        // line 56
        yield $this->extensions['Symfony\Bridge\Twig\Extension\RoutingExtension']->getPath("dashboard_event_new");
        yield "\">
                        <i class=\"metismenu-icon\"></i>
                        Ajouter un Évènement
                    </a>
                    </li>
                    <li>
                        <a href=\"";
        // line 62
        yield $this->extensions['Symfony\Bridge\Twig\Extension\RoutingExtension']->getPath("dashboard_event_index");
        yield "\">
                            <i class=\"metismenu-icon\"></i>
                            Liste des Évènements
                        </a>
                        </li>

                </ul>
                </li>

                <li class=\"app-sidebar__heading\">Billets</li>
                <li>
                    <a href=\"#\">
                        <i class=\"metismenu-icon pe-7s-ticket\"></i>
                        Gestion Billets
                        <i class=\"metismenu-state-icon pe-7s-angle-down caret-left\"></i>
                    </a>
                    <ul>
                        <li>
                            <a href=\"";
        // line 80
        yield $this->extensions['Symfony\Bridge\Twig\Extension\RoutingExtension']->getPath("app_billet_new");
        yield "\">
                                <i class=\"metismenu-icon\"></i>
                                Ajouter un billet
                            </a>
                        </li>
                        <li>
                            <a href=\"";
        // line 86
        yield $this->extensions['Symfony\Bridge\Twig\Extension\RoutingExtension']->getPath("app_billet_index");
        yield "\">
                                <i class=\"metismenu-icon\"></i>
                                Liste des billets
                            </a>
                        </li>
                    </ul>
                </li>
                <li class=\"app-sidebar__heading\">Espaces</li>
<li>
    <a href=\"#\">
        <i class=\"metismenu-icon pe-7s-map-marker\"></i>
        Gestion Espaces
        <i class=\"metismenu-state-icon pe-7s-angle-down caret-left\"></i>
    </a>
    <ul>
        <li>
            <a href=\"";
        // line 102
        yield $this->extensions['Symfony\Bridge\Twig\Extension\RoutingExtension']->getPath("dashboard_espace_new");
        yield "\">
                <i class=\"metismenu-icon\"></i>
                Ajouter un Espace
            </a>
        </li>
        <li>
            <a href=\"";
        // line 108
        yield $this->extensions['Symfony\Bridge\Twig\Extension\RoutingExtension']->getPath("dashboard_espace_index");
        yield "\">
                <i class=\"metismenu-icon\"></i>
                Liste des Espaces
            </a>
        </li>
    </ul>
</li>
<li class=\"app-sidebar__heading\">Organisateurs</li>
<li>
    <a href=\"#\">
        <i class=\"metismenu-icon pe-7s-id\"></i>
        Gestion Organisateurs
        <i class=\"metismenu-state-icon pe-7s-angle-down caret-left\"></i>
    </a>
    <ul>
        <li>
            <a href=\"";
        // line 124
        yield $this->extensions['Symfony\Bridge\Twig\Extension\RoutingExtension']->getPath("app_organisateur_index");
        yield "\">
                <i class=\"metismenu-icon\"></i>
                Liste des organisateurs
            </a>
        </li>
    </ul>
</li>

                <li class=\"app-sidebar__heading\">PRO Version</li>
                <li>
                    <a href=\"https://dashboardpack.com/theme-details/architectui-dashboard-html-pro/\"
                        target=\"_blank\">
                        <i class=\"metismenu-icon pe-7s-graph2\">
                        </i>
                        Buy me a coffee!
                    </a>
                </li>
            </ul>
        </div>
    </div>
</div>";
        
        $__internal_5a27a8ba21ca79b61932376b2fa922d2->leave($__internal_5a27a8ba21ca79b61932376b2fa922d2_prof);

        
        $__internal_6f47bbe9983af81f1e7450e9a3e3768f->leave($__internal_6f47bbe9983af81f1e7450e9a3e3768f_prof);

        yield from [];
    }

    /**
     * @codeCoverageIgnore
     */
    public function getTemplateName(): string
    {
        return "partialsBack/sidebar.html.twig";
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
        return array (  194 => 124,  175 => 108,  166 => 102,  147 => 86,  138 => 80,  117 => 62,  108 => 56,  88 => 39,  48 => 1,);
    }

    public function getSourceContext(): Source
    {
        return new Source("<div class=\"app-sidebar sidebar-shadow\">
    <div class=\"app-header__logo\">
        <div class=\"logo-src\"></div>
        <div class=\"header__pane ml-auto\">
            <div>
                <button type=\"button\" class=\"hamburger close-sidebar-btn hamburger--elastic\"
                    data-class=\"closed-sidebar\">
                    <span class=\"hamburger-box\">
                        <span class=\"hamburger-inner\"></span>
                    </span>
                </button>
            </div>
        </div>
    </div>
    <div class=\"app-header__mobile-menu\">
        <div>
            <button type=\"button\" class=\"hamburger hamburger--elastic mobile-toggle-nav\">
                <span class=\"hamburger-box\">
                    <span class=\"hamburger-inner\"></span>
                </span>
            </button>
        </div>
    </div>
    <div class=\"app-header__menu\">
        <span>
            <button type=\"button\"
                class=\"btn-icon btn-icon-only btn btn-primary btn-sm mobile-toggle-header-nav\">
                <span class=\"btn-icon-wrapper\">
                    <i class=\"fa fa-ellipsis-v fa-w-6\"></i>
                </span>
            </button>
        </span>
    </div>
    <div class=\"scrollbar-sidebar\">
        <div class=\"app-sidebar__inner\">
            <ul class=\"vertical-nav-menu\">
                <li class=\"app-sidebar__heading\">Dashboards</li>
                <li>
                    <a href=\"{{ path('app_dashboard') }}\" class=\"mm-active\">
                        <i class=\"metismenu-icon pe-7s-rocket\"></i>
                        Dashboard Example 1
                    </a>
                </li>
            
                
                <li class=\"app-sidebar__heading\">Évènements</li>
                <li>
                <a href=\"#\">
                    <i class=\"metismenu-icon pe-7s-date\"></i>
                    Gestion Évènements
                    <i class=\"metismenu-state-icon pe-7s-angle-down caret-left\"></i>
                </a>
                <ul>
                    
                    <li>
                    <a href=\"{{ path('dashboard_event_new') }}\">
                        <i class=\"metismenu-icon\"></i>
                        Ajouter un Évènement
                    </a>
                    </li>
                    <li>
                        <a href=\"{{ path('dashboard_event_index') }}\">
                            <i class=\"metismenu-icon\"></i>
                            Liste des Évènements
                        </a>
                        </li>

                </ul>
                </li>

                <li class=\"app-sidebar__heading\">Billets</li>
                <li>
                    <a href=\"#\">
                        <i class=\"metismenu-icon pe-7s-ticket\"></i>
                        Gestion Billets
                        <i class=\"metismenu-state-icon pe-7s-angle-down caret-left\"></i>
                    </a>
                    <ul>
                        <li>
                            <a href=\"{{ path('app_billet_new') }}\">
                                <i class=\"metismenu-icon\"></i>
                                Ajouter un billet
                            </a>
                        </li>
                        <li>
                            <a href=\"{{ path('app_billet_index') }}\">
                                <i class=\"metismenu-icon\"></i>
                                Liste des billets
                            </a>
                        </li>
                    </ul>
                </li>
                <li class=\"app-sidebar__heading\">Espaces</li>
<li>
    <a href=\"#\">
        <i class=\"metismenu-icon pe-7s-map-marker\"></i>
        Gestion Espaces
        <i class=\"metismenu-state-icon pe-7s-angle-down caret-left\"></i>
    </a>
    <ul>
        <li>
            <a href=\"{{ path('dashboard_espace_new') }}\">
                <i class=\"metismenu-icon\"></i>
                Ajouter un Espace
            </a>
        </li>
        <li>
            <a href=\"{{ path('dashboard_espace_index') }}\">
                <i class=\"metismenu-icon\"></i>
                Liste des Espaces
            </a>
        </li>
    </ul>
</li>
<li class=\"app-sidebar__heading\">Organisateurs</li>
<li>
    <a href=\"#\">
        <i class=\"metismenu-icon pe-7s-id\"></i>
        Gestion Organisateurs
        <i class=\"metismenu-state-icon pe-7s-angle-down caret-left\"></i>
    </a>
    <ul>
        <li>
            <a href=\"{{ path('app_organisateur_index') }}\">
                <i class=\"metismenu-icon\"></i>
                Liste des organisateurs
            </a>
        </li>
    </ul>
</li>

                <li class=\"app-sidebar__heading\">PRO Version</li>
                <li>
                    <a href=\"https://dashboardpack.com/theme-details/architectui-dashboard-html-pro/\"
                        target=\"_blank\">
                        <i class=\"metismenu-icon pe-7s-graph2\">
                        </i>
                        Buy me a coffee!
                    </a>
                </li>
            </ul>
        </div>
    </div>
</div>", "partialsBack/sidebar.html.twig", "C:\\wamp64\\gestion_espace_symfony\\templates\\partialsBack\\sidebar.html.twig");
    }
}
