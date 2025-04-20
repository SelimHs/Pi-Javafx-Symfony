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

/* espace/show.html.twig */
class __TwigTemplate_a621034fb1184e4773f837599d5684bc extends Template
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
        $__internal_5a27a8ba21ca79b61932376b2fa922d2->enter($__internal_5a27a8ba21ca79b61932376b2fa922d2_prof = new \Twig\Profiler\Profile($this->getTemplateName(), "template", "espace/show.html.twig"));

        $__internal_6f47bbe9983af81f1e7450e9a3e3768f = $this->extensions["Symfony\\Bridge\\Twig\\Extension\\ProfilerExtension"];
        $__internal_6f47bbe9983af81f1e7450e9a3e3768f->enter($__internal_6f47bbe9983af81f1e7450e9a3e3768f_prof = new \Twig\Profiler\Profile($this->getTemplateName(), "template", "espace/show.html.twig"));

        $this->parent = $this->loadTemplate("base.html.twig", "espace/show.html.twig", 1);
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

        yield "Détails de l'Espace";
        
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
        yield "  <!-- Section Best Deal Adaptée pour l'Espace avec animations modernes -->
  <div class=\"section best-deal animate__animated animate__fadeIn\">
    <div class=\"container\">
      <div class=\"row\">
        <!-- En-tête -->
        <div class=\"col-lg-4\">
          <div class=\"section-heading animate__animated animate__fadeInLeft\">
            <h6>| Best Offer</h6>
            <h2>";
        // line 14
        yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape(CoreExtension::getAttribute($this->env, $this->source, (isset($context["espace"]) || array_key_exists("espace", $context) ? $context["espace"] : (function () { throw new RuntimeError('Variable "espace" does not exist.', 14, $this->source); })()), "nomEspace", [], "any", false, false, false, 14), "html", null, true);
        yield "</h2>
          </div>
        </div>
        <!-- Contenu en onglets -->
        <div class=\"col-lg-12\">
          <div class=\"tabs-content animate__animated animate__fadeInUp\">
            <div class=\"row\">
              <div class=\"nav-wrapper\">
                <ul class=\"nav nav-tabs\" role=\"tablist\">
                  <li class=\"nav-item\" role=\"presentation\">
                    <button class=\"nav-link active animate__animated animate__fadeInUp\" id=\"details-tab\" data-bs-toggle=\"tab\" data-bs-target=\"#details\" type=\"button\" role=\"tab\" aria-controls=\"details\" aria-selected=\"true\">
                      Détails
                    </button>
                  </li>
                  <li class=\"nav-item\" role=\"presentation\">
                    <button class=\"nav-link animate__animated animate__fadeInUp\" id=\"info-tab\" data-bs-toggle=\"tab\" data-bs-target=\"#info\" type=\"button\" role=\"tab\" aria-controls=\"info\" aria-selected=\"false\">
                     🛑LIVE 🎞️🎥
                    </button>
                  </li>
                  <li class=\"nav-item\" role=\"presentation\">
                    <button class=\"nav-link animate__animated animate__fadeInUp\" id=\"organisateurs-tab\" data-bs-toggle=\"tab\" data-bs-target=\"#organisateurs\" type=\"button\" role=\"tab\" aria-controls=\"organisateurs\" aria-selected=\"false\">
                      Organisateurs
                    </button>
                  </li>
                </ul>
              </div>
              <div class=\"tab-content mt-4\" id=\"myTabContent\">
                ";
        // line 42
        yield "                <div class=\"tab-pane fade show active animate__animated animate__fadeIn\" id=\"details\" role=\"tabpanel\" aria-labelledby=\"details-tab\">
                  <div class=\"row\">
                    <!-- Colonne de gauche : tableau récapitulatif -->
                    <div class=\"col-lg-3\">
                      <div class=\"info-table animate__animated animate__fadeInLeft\">
                        <ul>
                          <li>Adresse <span>";
        // line 48
        yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape(CoreExtension::getAttribute($this->env, $this->source, (isset($context["espace"]) || array_key_exists("espace", $context) ? $context["espace"] : (function () { throw new RuntimeError('Variable "espace" does not exist.', 48, $this->source); })()), "adresse", [], "any", false, false, false, 48), "html", null, true);
        yield "</span></li>
                          <li>Capacité <span>";
        // line 49
        yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape(CoreExtension::getAttribute($this->env, $this->source, (isset($context["espace"]) || array_key_exists("espace", $context) ? $context["espace"] : (function () { throw new RuntimeError('Variable "espace" does not exist.', 49, $this->source); })()), "capacite", [], "any", false, false, false, 49), "html", null, true);
        yield "</span></li>
                          <li>Disponibilité <span>";
        // line 50
        yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape(CoreExtension::getAttribute($this->env, $this->source, (isset($context["espace"]) || array_key_exists("espace", $context) ? $context["espace"] : (function () { throw new RuntimeError('Variable "espace" does not exist.', 50, $this->source); })()), "disponibilite", [], "any", false, false, false, 50), "html", null, true);
        yield "</span></li>
                          <li>Prix <span>";
        // line 51
        yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape(CoreExtension::getAttribute($this->env, $this->source, (isset($context["espace"]) || array_key_exists("espace", $context) ? $context["espace"] : (function () { throw new RuntimeError('Variable "espace" does not exist.', 51, $this->source); })()), "prix", [], "any", false, false, false, 51), "html", null, true);
        yield " Dt</span></li>
                          <li>Type <span>";
        // line 52
        yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape(CoreExtension::getAttribute($this->env, $this->source, (isset($context["espace"]) || array_key_exists("espace", $context) ? $context["espace"] : (function () { throw new RuntimeError('Variable "espace" does not exist.', 52, $this->source); })()), "TypeEspace", [], "any", false, false, false, 52), "html", null, true);
        yield "</span></li>
                        </ul>
                      </div>
                    </div>
                    <!-- Colonne centrale : Image principale -->
                    <div class=\"col-lg-6\">
                      <div class=\"animate__animated animate__zoomIn\">
                        <img src=\"";
        // line 59
        yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape($this->extensions['Symfony\Bridge\Twig\Extension\AssetExtension']->getAssetUrl(("uploads/" . CoreExtension::getAttribute($this->env, $this->source, (isset($context["espace"]) || array_key_exists("espace", $context) ? $context["espace"] : (function () { throw new RuntimeError('Variable "espace" does not exist.', 59, $this->source); })()), "image", [], "any", false, false, false, 59))), "html", null, true);
        yield "\" alt=\"Image de l'espace\" class=\"img-fluid\" style=\"border-radius:8px;\">
                      </div>
                    </div>
                    <!-- Colonne droite : Détails textuels -->
                    <div class=\"col-lg-3\">
                      <div class=\"animate__animated animate__fadeInRight\">
                        <h4>Détails</h4>
                        ";
        // line 66
        if ((CoreExtension::getAttribute($this->env, $this->source, ($context["espace"] ?? null), "description", [], "any", true, true, false, 66) &&  !Twig\Extension\CoreExtension::testEmpty(CoreExtension::getAttribute($this->env, $this->source, (isset($context["espace"]) || array_key_exists("espace", $context) ? $context["espace"] : (function () { throw new RuntimeError('Variable "espace" does not exist.', 66, $this->source); })()), "description", [], "any", false, false, false, 66)))) {
            // line 67
            yield "                          <p>";
            yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape(CoreExtension::getAttribute($this->env, $this->source, (isset($context["espace"]) || array_key_exists("espace", $context) ? $context["espace"] : (function () { throw new RuntimeError('Variable "espace" does not exist.', 67, $this->source); })()), "description", [], "any", false, false, false, 67), "html", null, true);
            yield "</p>
                        ";
        } else {
            // line 69
            yield "                          <p>Aucune description supplémentaire n'est disponible pour cet espace.</p>
                        ";
        }
        // line 71
        yield "                        <div class=\"icon-button mt-3\">
                          <a href=\"";
        // line 72
        yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape($this->extensions['Symfony\Bridge\Twig\Extension\RoutingExtension']->getPath("app_espace_edit", ["idEspace" => CoreExtension::getAttribute($this->env, $this->source, (isset($context["espace"]) || array_key_exists("espace", $context) ? $context["espace"] : (function () { throw new RuntimeError('Variable "espace" does not exist.', 72, $this->source); })()), "idEspace", [], "any", false, false, false, 72)]), "html", null, true);
        yield "\" class=\"btn btn-primary\">
                            <i class=\"fa fa-calendar\"></i> Modifier l'espace
                          </a>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
                ";
        // line 81
        yield "                <div class=\"tab-pane fade animate__animated animate__fadeIn\" id=\"info\" role=\"tabpanel\" aria-labelledby=\"info-tab\">
  <div class=\"row\">
    <div class=\"col-lg-12\">
      <div class=\"animate__animated animate__fadeInUp\">
        <h4>🎥 Vue en Direct</h4>
        <p>Flux en direct depuis l’espace <strong>";
        // line 86
        yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape(CoreExtension::getAttribute($this->env, $this->source, (isset($context["espace"]) || array_key_exists("espace", $context) ? $context["espace"] : (function () { throw new RuntimeError('Variable "espace" does not exist.', 86, $this->source); })()), "nomEspace", [], "any", false, false, false, 86), "html", null, true);
        yield "</strong> :</p>
        <div class=\"ratio ratio-16x9\" style=\"border-radius: 12px; overflow: hidden; box-shadow: 0 5px 15px rgba(0,0,0,0.1);\">
          <iframe src=\"";
        // line 88
        yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape((isset($context["liveURL"]) || array_key_exists("liveURL", $context) ? $context["liveURL"] : (function () { throw new RuntimeError('Variable "liveURL" does not exist.', 88, $this->source); })()), "html", null, true);
        yield "\" allowfullscreen frameborder=\"0\"></iframe>
        </div>
      </div>
    </div>
  </div>
</div>

                ";
        // line 96
        yield "                <div class=\"tab-pane fade animate__animated animate__fadeIn\" id=\"organisateurs\" role=\"tabpanel\" aria-labelledby=\"organisateurs-tab\">
                  <div class=\"row\">
                    <div class=\"col-lg-12\">
                      <div class=\"animate__animated animate__fadeInUp\">
                        <h4>Organisateurs dans cet espace</h4>
                        <p>
                          Nos organisateurs ne se contentent pas de coordonner l'événement ! Ce sont de véritables artistes qui transforment la salle en un lieu unique, selon vos envies.
                          Ils collaborent étroitement avec vous pour créer une ambiance sur mesure qui reflète votre personnalité et vos besoins.
                        </p>
                      </div>
                      <div class=\"row mt-4\">
                        ";
        // line 108
        yield "                        <div class=\"col-md-6 mb-4\">
                          <a href=\"";
        // line 109
        yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape($this->extensions['Symfony\Bridge\Twig\Extension\RoutingExtension']->getPath("app_organisateur_new", ["idEspace" => CoreExtension::getAttribute($this->env, $this->source, (isset($context["espace"]) || array_key_exists("espace", $context) ? $context["espace"] : (function () { throw new RuntimeError('Variable "espace" does not exist.', 109, $this->source); })()), "idEspace", [], "any", false, false, false, 109)]), "html", null, true);
        yield "\" class=\"text-decoration-none\">
                            <div class=\"card h-100 border-0 shadow-lg add-organisateur-card animate__animated animate__flipInY\">
                              <div class=\"card-body text-center\">
                                <h5 class=\"card-title text-white\"><i class=\"fa fa-plus fa-3x\"></i></h5>
                                <p class=\"card-text text-white mt-2\" style=\"font-size: 1.1rem; font-weight: bold;\">Ajouter un organisateur</p>
                              </div>
                            </div>
                          </a>
                        </div>
                        ";
        // line 119
        yield "                        ";
        if ( !Twig\Extension\CoreExtension::testEmpty(CoreExtension::getAttribute($this->env, $this->source, (isset($context["espace"]) || array_key_exists("espace", $context) ? $context["espace"] : (function () { throw new RuntimeError('Variable "espace" does not exist.', 119, $this->source); })()), "organisateurs", [], "any", false, false, false, 119))) {
            // line 120
            yield "                          ";
            $context['_parent'] = $context;
            $context['_seq'] = CoreExtension::ensureTraversable(CoreExtension::getAttribute($this->env, $this->source, (isset($context["espace"]) || array_key_exists("espace", $context) ? $context["espace"] : (function () { throw new RuntimeError('Variable "espace" does not exist.', 120, $this->source); })()), "organisateurs", [], "any", false, false, false, 120));
            foreach ($context['_seq'] as $context["_key"] => $context["organisateur"]) {
                // line 121
                yield "                            <div class=\"col-md-6 mb-4\">
                              <div class=\"card h-100 border-0 shadow-lg animate__animated animate__fadeInUp\">
                                <div class=\"card-body\">
                                  <h5 class=\"card-title\">";
                // line 124
                yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape(CoreExtension::getAttribute($this->env, $this->source, $context["organisateur"], "nom_org", [], "any", false, false, false, 124), "html", null, true);
                yield " ";
                yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape(CoreExtension::getAttribute($this->env, $this->source, $context["organisateur"], "prenom_org", [], "any", false, false, false, 124), "html", null, true);
                yield "</h5>
                                  <p class=\"card-text\"><strong>Téléphone :</strong> ";
                // line 125
                yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape(CoreExtension::getAttribute($this->env, $this->source, $context["organisateur"], "telef", [], "any", false, false, false, 125), "html", null, true);
                yield "</p>
                                  <p class=\"card-text\"><strong>Description :</strong><br>";
                // line 126
                yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape(CoreExtension::getAttribute($this->env, $this->source, $context["organisateur"], "description_org", [], "any", false, false, false, 126), "html", null, true);
                yield "</p>
                                </div>
                                <div class=\"card-footer bg-transparent border-0 d-flex justify-content-between\">
                                  <a href=\"";
                // line 129
                yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape($this->extensions['Symfony\Bridge\Twig\Extension\RoutingExtension']->getPath("app_organisateur_edit", ["id_org" => CoreExtension::getAttribute($this->env, $this->source, $context["organisateur"], "id_org", [], "any", false, false, false, 129)]), "html", null, true);
                yield "\" class=\"btn btn-sm btn-outline-primary\">Modifier</a>
                                  <form method=\"post\" action=\"";
                // line 130
                yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape($this->extensions['Symfony\Bridge\Twig\Extension\RoutingExtension']->getPath("app_organisateur_delete", ["id_org" => CoreExtension::getAttribute($this->env, $this->source, $context["organisateur"], "id_org", [], "any", false, false, false, 130)]), "html", null, true);
                yield "\" onsubmit=\"return confirm('Êtes-vous sûr de vouloir supprimer cet organisateur ?');\">
                                    <input type=\"hidden\" name=\"_token\" value=\"";
                // line 131
                yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape($this->env->getRuntime('Symfony\Component\Form\FormRenderer')->renderCsrfToken(("delete" . CoreExtension::getAttribute($this->env, $this->source, $context["organisateur"], "id_org", [], "any", false, false, false, 131))), "html", null, true);
                yield "\">
                                    <button class=\"btn btn-sm btn-outline-danger\">Supprimer</button>
                                  </form>
                                </div>
                              </div>
                            </div>
                          ";
            }
            $_parent = $context['_parent'];
            unset($context['_seq'], $context['_key'], $context['organisateur'], $context['_parent']);
            $context = array_intersect_key($context, $_parent) + $_parent;
            // line 138
            yield "                        ";
        } else {
            // line 139
            yield "                          <p class=\"text-muted\">Aucun organisateur assigné à cet espace.</p>
                        ";
        }
        // line 141
        yield "                      </div>
                    </div>
                  </div>
                </div>
                ";
        // line 146
        yield "              </div> <!-- End tab-content -->
            </div> <!-- End row -->
          </div> <!-- End tabs-content -->
        </div> <!-- End col-lg-12 -->
      </div> <!-- End row -->
    </div> <!-- End container -->
  </div> <!-- End section best-deal -->

  <!-- Footer -->
  <footer class=\"footer-no-gap\">
    <div class=\"container\">
      <div class=\"col-lg-12 text-center\">
        <p>
          Copyright © 2048 Villa Agency Co., Ltd.
          All rights reserved. Design:
          <a rel=\"nofollow\" href=\"https://templatemo.com\" target=\"_blank\">TemplateMo</a>
        </p>
      </div>
    </div>
  </footer>

  ";
        // line 168
        yield "  <style>
    .add-organisateur-card {
      min-height: 220px;
      background: linear-gradient(135deg, #6a11cb 0%, #2575fc 100%);
      border-radius: 15px;
      display: flex;
      justify-content: center;
      align-items: center;
      transition: transform 0.3s, box-shadow 0.3s;
    }
    .add-organisateur-card:hover {
      transform: translateY(-5px);
      box-shadow: 0 8px 16px rgba(0, 0, 0, 0.2);
    }
    <style>
  .ratio-16x9 {
    position: relative;
    width: 100%;
    padding-bottom: 56.25%;
    height: 0;
  }
  .ratio-16x9 iframe {
    position: absolute;
    width: 100%;
    height: 100%;
    top: 0;
    left: 0;
  }
</style>

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
        return "espace/show.html.twig";
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
        return array (  346 => 168,  323 => 146,  317 => 141,  313 => 139,  310 => 138,  297 => 131,  293 => 130,  289 => 129,  283 => 126,  279 => 125,  273 => 124,  268 => 121,  263 => 120,  260 => 119,  248 => 109,  245 => 108,  232 => 96,  222 => 88,  217 => 86,  210 => 81,  199 => 72,  196 => 71,  192 => 69,  186 => 67,  184 => 66,  174 => 59,  164 => 52,  160 => 51,  156 => 50,  152 => 49,  148 => 48,  140 => 42,  110 => 14,  100 => 6,  87 => 5,  64 => 3,  41 => 1,);
    }

    public function getSourceContext(): Source
    {
        return new Source("{% extends 'base.html.twig' %}

{% block title %}Détails de l'Espace{% endblock %}

{% block body %}
  <!-- Section Best Deal Adaptée pour l'Espace avec animations modernes -->
  <div class=\"section best-deal animate__animated animate__fadeIn\">
    <div class=\"container\">
      <div class=\"row\">
        <!-- En-tête -->
        <div class=\"col-lg-4\">
          <div class=\"section-heading animate__animated animate__fadeInLeft\">
            <h6>| Best Offer</h6>
            <h2>{{ espace.nomEspace }}</h2>
          </div>
        </div>
        <!-- Contenu en onglets -->
        <div class=\"col-lg-12\">
          <div class=\"tabs-content animate__animated animate__fadeInUp\">
            <div class=\"row\">
              <div class=\"nav-wrapper\">
                <ul class=\"nav nav-tabs\" role=\"tablist\">
                  <li class=\"nav-item\" role=\"presentation\">
                    <button class=\"nav-link active animate__animated animate__fadeInUp\" id=\"details-tab\" data-bs-toggle=\"tab\" data-bs-target=\"#details\" type=\"button\" role=\"tab\" aria-controls=\"details\" aria-selected=\"true\">
                      Détails
                    </button>
                  </li>
                  <li class=\"nav-item\" role=\"presentation\">
                    <button class=\"nav-link animate__animated animate__fadeInUp\" id=\"info-tab\" data-bs-toggle=\"tab\" data-bs-target=\"#info\" type=\"button\" role=\"tab\" aria-controls=\"info\" aria-selected=\"false\">
                     🛑LIVE 🎞️🎥
                    </button>
                  </li>
                  <li class=\"nav-item\" role=\"presentation\">
                    <button class=\"nav-link animate__animated animate__fadeInUp\" id=\"organisateurs-tab\" data-bs-toggle=\"tab\" data-bs-target=\"#organisateurs\" type=\"button\" role=\"tab\" aria-controls=\"organisateurs\" aria-selected=\"false\">
                      Organisateurs
                    </button>
                  </li>
                </ul>
              </div>
              <div class=\"tab-content mt-4\" id=\"myTabContent\">
                {# Onglet Détails #}
                <div class=\"tab-pane fade show active animate__animated animate__fadeIn\" id=\"details\" role=\"tabpanel\" aria-labelledby=\"details-tab\">
                  <div class=\"row\">
                    <!-- Colonne de gauche : tableau récapitulatif -->
                    <div class=\"col-lg-3\">
                      <div class=\"info-table animate__animated animate__fadeInLeft\">
                        <ul>
                          <li>Adresse <span>{{ espace.adresse }}</span></li>
                          <li>Capacité <span>{{ espace.capacite }}</span></li>
                          <li>Disponibilité <span>{{ espace.disponibilite }}</span></li>
                          <li>Prix <span>{{ espace.prix }} Dt</span></li>
                          <li>Type <span>{{ espace.TypeEspace }}</span></li>
                        </ul>
                      </div>
                    </div>
                    <!-- Colonne centrale : Image principale -->
                    <div class=\"col-lg-6\">
                      <div class=\"animate__animated animate__zoomIn\">
                        <img src=\"{{ asset('uploads/' ~ espace.image) }}\" alt=\"Image de l'espace\" class=\"img-fluid\" style=\"border-radius:8px;\">
                      </div>
                    </div>
                    <!-- Colonne droite : Détails textuels -->
                    <div class=\"col-lg-3\">
                      <div class=\"animate__animated animate__fadeInRight\">
                        <h4>Détails</h4>
                        {% if espace.description is defined and espace.description is not empty %}
                          <p>{{ espace.description }}</p>
                        {% else %}
                          <p>Aucune description supplémentaire n'est disponible pour cet espace.</p>
                        {% endif %}
                        <div class=\"icon-button mt-3\">
                          <a href=\"{{ path('app_espace_edit', {'idEspace': espace.idEspace}) }}\" class=\"btn btn-primary\">
                            <i class=\"fa fa-calendar\"></i> Modifier l'espace
                          </a>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
                {# Onglet Infos Supplémentaires #}
                <div class=\"tab-pane fade animate__animated animate__fadeIn\" id=\"info\" role=\"tabpanel\" aria-labelledby=\"info-tab\">
  <div class=\"row\">
    <div class=\"col-lg-12\">
      <div class=\"animate__animated animate__fadeInUp\">
        <h4>🎥 Vue en Direct</h4>
        <p>Flux en direct depuis l’espace <strong>{{ espace.nomEspace }}</strong> :</p>
        <div class=\"ratio ratio-16x9\" style=\"border-radius: 12px; overflow: hidden; box-shadow: 0 5px 15px rgba(0,0,0,0.1);\">
          <iframe src=\"{{ liveURL }}\" allowfullscreen frameborder=\"0\"></iframe>
        </div>
      </div>
    </div>
  </div>
</div>

                {# Onglet Organisateurs #}
                <div class=\"tab-pane fade animate__animated animate__fadeIn\" id=\"organisateurs\" role=\"tabpanel\" aria-labelledby=\"organisateurs-tab\">
                  <div class=\"row\">
                    <div class=\"col-lg-12\">
                      <div class=\"animate__animated animate__fadeInUp\">
                        <h4>Organisateurs dans cet espace</h4>
                        <p>
                          Nos organisateurs ne se contentent pas de coordonner l'événement ! Ce sont de véritables artistes qui transforment la salle en un lieu unique, selon vos envies.
                          Ils collaborent étroitement avec vous pour créer une ambiance sur mesure qui reflète votre personnalité et vos besoins.
                        </p>
                      </div>
                      <div class=\"row mt-4\">
                        {# Carte d'ajout d'organisateur améliorée #}
                        <div class=\"col-md-6 mb-4\">
                          <a href=\"{{ path('app_organisateur_new', {'idEspace': espace.idEspace}) }}\" class=\"text-decoration-none\">
                            <div class=\"card h-100 border-0 shadow-lg add-organisateur-card animate__animated animate__flipInY\">
                              <div class=\"card-body text-center\">
                                <h5 class=\"card-title text-white\"><i class=\"fa fa-plus fa-3x\"></i></h5>
                                <p class=\"card-text text-white mt-2\" style=\"font-size: 1.1rem; font-weight: bold;\">Ajouter un organisateur</p>
                              </div>
                            </div>
                          </a>
                        </div>
                        {# Boucle sur les organisateurs existants #}
                        {% if espace.organisateurs is not empty %}
                          {% for organisateur in espace.organisateurs %}
                            <div class=\"col-md-6 mb-4\">
                              <div class=\"card h-100 border-0 shadow-lg animate__animated animate__fadeInUp\">
                                <div class=\"card-body\">
                                  <h5 class=\"card-title\">{{ organisateur.nom_org }} {{ organisateur.prenom_org }}</h5>
                                  <p class=\"card-text\"><strong>Téléphone :</strong> {{ organisateur.telef }}</p>
                                  <p class=\"card-text\"><strong>Description :</strong><br>{{ organisateur.description_org }}</p>
                                </div>
                                <div class=\"card-footer bg-transparent border-0 d-flex justify-content-between\">
                                  <a href=\"{{ path('app_organisateur_edit', {'id_org': organisateur.id_org}) }}\" class=\"btn btn-sm btn-outline-primary\">Modifier</a>
                                  <form method=\"post\" action=\"{{ path('app_organisateur_delete', {'id_org': organisateur.id_org}) }}\" onsubmit=\"return confirm('Êtes-vous sûr de vouloir supprimer cet organisateur ?');\">
                                    <input type=\"hidden\" name=\"_token\" value=\"{{ csrf_token('delete' ~ organisateur.id_org) }}\">
                                    <button class=\"btn btn-sm btn-outline-danger\">Supprimer</button>
                                  </form>
                                </div>
                              </div>
                            </div>
                          {% endfor %}
                        {% else %}
                          <p class=\"text-muted\">Aucun organisateur assigné à cet espace.</p>
                        {% endif %}
                      </div>
                    </div>
                  </div>
                </div>
                {# Fin des onglets #}
              </div> <!-- End tab-content -->
            </div> <!-- End row -->
          </div> <!-- End tabs-content -->
        </div> <!-- End col-lg-12 -->
      </div> <!-- End row -->
    </div> <!-- End container -->
  </div> <!-- End section best-deal -->

  <!-- Footer -->
  <footer class=\"footer-no-gap\">
    <div class=\"container\">
      <div class=\"col-lg-12 text-center\">
        <p>
          Copyright © 2048 Villa Agency Co., Ltd.
          All rights reserved. Design:
          <a rel=\"nofollow\" href=\"https://templatemo.com\" target=\"_blank\">TemplateMo</a>
        </p>
      </div>
    </div>
  </footer>

  {# Styles pour la carte d'ajout d'organisateur #}
  <style>
    .add-organisateur-card {
      min-height: 220px;
      background: linear-gradient(135deg, #6a11cb 0%, #2575fc 100%);
      border-radius: 15px;
      display: flex;
      justify-content: center;
      align-items: center;
      transition: transform 0.3s, box-shadow 0.3s;
    }
    .add-organisateur-card:hover {
      transform: translateY(-5px);
      box-shadow: 0 8px 16px rgba(0, 0, 0, 0.2);
    }
    <style>
  .ratio-16x9 {
    position: relative;
    width: 100%;
    padding-bottom: 56.25%;
    height: 0;
  }
  .ratio-16x9 iframe {
    position: absolute;
    width: 100%;
    height: 100%;
    top: 0;
    left: 0;
  }
</style>

  </style>
{% endblock %}
", "espace/show.html.twig", "C:\\wamp64\\gestion_espace_symfony\\templates\\espace\\show.html.twig");
    }
}
