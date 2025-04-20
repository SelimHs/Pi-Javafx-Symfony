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
        yield "<div class=\"section best-deal animate__animated animate__fadeIn\">
  <div class=\"container\">
    <div class=\"row\">
      <!-- En-tête -->
      <div class=\"col-lg-4\">
        <div class=\"section-heading animate__animated animate__fadeInLeft\">
          <h6>| Best Offer</h6>
          <h2>";
        // line 13
        yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape(CoreExtension::getAttribute($this->env, $this->source, (isset($context["espace"]) || array_key_exists("espace", $context) ? $context["espace"] : (function () { throw new RuntimeError('Variable "espace" does not exist.', 13, $this->source); })()), "nomEspace", [], "any", false, false, false, 13), "html", null, true);
        yield "</h2>
        </div>
      </div>

      <!-- Onglets -->
      <div class=\"col-lg-12\">
        <div class=\"tabs-content animate__animated animate__fadeInUp\">
          <div class=\"row\">
            <div class=\"nav-wrapper\">
              <ul class=\"nav nav-tabs\" role=\"tablist\">
                <li class=\"nav-item\" role=\"presentation\">
                  <button class=\"nav-link active\" id=\"details-tab\" data-bs-toggle=\"tab\" data-bs-target=\"#details\" type=\"button\" role=\"tab\">Détails</button>
                </li>
                <li class=\"nav-item\" role=\"presentation\">
                  <button class=\"nav-link\" id=\"info-tab\" data-bs-toggle=\"tab\" data-bs-target=\"#info\" type=\"button\" role=\"tab\">🛑 LIVE 🎞️🎥</button>
                </li>
                <li class=\"nav-item\" role=\"presentation\">
                  <button class=\"nav-link\" id=\"organisateurs-tab\" data-bs-toggle=\"tab\" data-bs-target=\"#organisateurs\" type=\"button\" role=\"tab\">Organisateurs</button>
                </li>
              </ul>
            </div>

            <div class=\"tab-content mt-4\" id=\"myTabContent\">
              <!-- Onglet Détails -->
              <div class=\"tab-pane fade show active\" id=\"details\" role=\"tabpanel\">
                <div class=\"row\">
                  <div class=\"col-lg-3\">
                    <div class=\"info-table\">
                      <ul>
                        <li>Adresse <span>";
        // line 42
        yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape(CoreExtension::getAttribute($this->env, $this->source, (isset($context["espace"]) || array_key_exists("espace", $context) ? $context["espace"] : (function () { throw new RuntimeError('Variable "espace" does not exist.', 42, $this->source); })()), "adresse", [], "any", false, false, false, 42), "html", null, true);
        yield "</span></li>
                        <li>Capacité <span>";
        // line 43
        yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape(CoreExtension::getAttribute($this->env, $this->source, (isset($context["espace"]) || array_key_exists("espace", $context) ? $context["espace"] : (function () { throw new RuntimeError('Variable "espace" does not exist.', 43, $this->source); })()), "capacite", [], "any", false, false, false, 43), "html", null, true);
        yield "</span></li>
                        <li>Disponibilité <span>";
        // line 44
        yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape(CoreExtension::getAttribute($this->env, $this->source, (isset($context["espace"]) || array_key_exists("espace", $context) ? $context["espace"] : (function () { throw new RuntimeError('Variable "espace" does not exist.', 44, $this->source); })()), "disponibilite", [], "any", false, false, false, 44), "html", null, true);
        yield "</span></li>
                        <li>Prix <span>";
        // line 45
        yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape(CoreExtension::getAttribute($this->env, $this->source, (isset($context["espace"]) || array_key_exists("espace", $context) ? $context["espace"] : (function () { throw new RuntimeError('Variable "espace" does not exist.', 45, $this->source); })()), "prix", [], "any", false, false, false, 45), "html", null, true);
        yield " Dt</span></li>
                        <li>Type <span>";
        // line 46
        yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape(CoreExtension::getAttribute($this->env, $this->source, (isset($context["espace"]) || array_key_exists("espace", $context) ? $context["espace"] : (function () { throw new RuntimeError('Variable "espace" does not exist.', 46, $this->source); })()), "TypeEspace", [], "any", false, false, false, 46), "html", null, true);
        yield "</span></li>
                      </ul>
                    </div>
                  </div>
                  <div class=\"col-lg-6\">
                    <img src=\"";
        // line 51
        yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape($this->extensions['Symfony\Bridge\Twig\Extension\AssetExtension']->getAssetUrl(("uploads/" . CoreExtension::getAttribute($this->env, $this->source, (isset($context["espace"]) || array_key_exists("espace", $context) ? $context["espace"] : (function () { throw new RuntimeError('Variable "espace" does not exist.', 51, $this->source); })()), "image", [], "any", false, false, false, 51))), "html", null, true);
        yield "\" alt=\"Image de l'espace\" class=\"img-fluid\" style=\"border-radius:8px;\">
                  </div>
                  <div class=\"col-lg-3\">
                    <h4>Détails</h4>
                    <p>";
        // line 55
        yield (((CoreExtension::getAttribute($this->env, $this->source, ($context["espace"] ?? null), "description", [], "any", true, true, false, 55) &&  !(null === CoreExtension::getAttribute($this->env, $this->source, (isset($context["espace"]) || array_key_exists("espace", $context) ? $context["espace"] : (function () { throw new RuntimeError('Variable "espace" does not exist.', 55, $this->source); })()), "description", [], "any", false, false, false, 55)))) ? ($this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape(CoreExtension::getAttribute($this->env, $this->source, (isset($context["espace"]) || array_key_exists("espace", $context) ? $context["espace"] : (function () { throw new RuntimeError('Variable "espace" does not exist.', 55, $this->source); })()), "description", [], "any", false, false, false, 55), "html", null, true)) : ("Aucune description supplémentaire n'est disponible pour cet espace."));
        yield "</p>
                    <div class=\"icon-button mt-3\">
                      <a href=\"";
        // line 57
        yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape($this->extensions['Symfony\Bridge\Twig\Extension\RoutingExtension']->getPath("app_espace_edit", ["idEspace" => CoreExtension::getAttribute($this->env, $this->source, (isset($context["espace"]) || array_key_exists("espace", $context) ? $context["espace"] : (function () { throw new RuntimeError('Variable "espace" does not exist.', 57, $this->source); })()), "idEspace", [], "any", false, false, false, 57)]), "html", null, true);
        yield "\" class=\"btn btn-primary\">
                        <i class=\"fa fa-calendar\"></i> Modifier l'espace
                      </a>
                    </div>
                  </div>
                </div>

                <!-- Carte Google Maps sans clé API (affichage direct via iframe) -->
<div class=\"col-lg-12 mt-4\">
  <div class=\"animate__animated animate__fadeInUp\">
    <h5>📍 Localisation sur Google Maps</h5>
    <div style=\"height: 400px; border-radius: 12px; overflow: hidden;\">
      <iframe
        width=\"100%\"
        height=\"100%\"
        frameborder=\"0\"
        style=\"border:0;\"
        allowfullscreen
        src=\"https://www.google.com/maps?q=";
        // line 75
        yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape(Twig\Extension\CoreExtension::urlencode(CoreExtension::getAttribute($this->env, $this->source, (isset($context["espace"]) || array_key_exists("espace", $context) ? $context["espace"] : (function () { throw new RuntimeError('Variable "espace" does not exist.', 75, $this->source); })()), "adresse", [], "any", false, false, false, 75)), "html", null, true);
        yield "&output=embed\">
      </iframe>
    </div>
  </div>
</div>




              <!-- Onglet LIVE -->
              <div class=\"tab-pane fade\" id=\"info\" role=\"tabpanel\">
                <div class=\"row\">
                  <div class=\"col-lg-12\">
                    <h4>🎥 Vue en Direct</h4>
                    <div id=\"live-container\" class=\"mt-3\" style=\"min-height: 400px;\">
                      <p class=\"text-muted\">🔄 Vérification de la disponibilité du flux live...</p>
                    </div>
                  </div>
                </div>
              </div>

              <!-- Onglet Organisateurs -->
              <div class=\"tab-pane fade\" id=\"organisateurs\" role=\"tabpanel\">
                <div class=\"row\">
                  <div class=\"col-lg-12\">
                    <h4>Organisateurs dans cet espace</h4>
                    <p>Nos organisateurs transforment chaque espace selon vos envies pour une ambiance sur mesure.</p>
                    <div class=\"row mt-4\">
                      <div class=\"col-md-6 mb-4\">
                        <a href=\"";
        // line 104
        yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape($this->extensions['Symfony\Bridge\Twig\Extension\RoutingExtension']->getPath("app_organisateur_new", ["idEspace" => CoreExtension::getAttribute($this->env, $this->source, (isset($context["espace"]) || array_key_exists("espace", $context) ? $context["espace"] : (function () { throw new RuntimeError('Variable "espace" does not exist.', 104, $this->source); })()), "idEspace", [], "any", false, false, false, 104)]), "html", null, true);
        yield "\" class=\"text-decoration-none\">
                          <div class=\"card h-100 border-0 shadow-lg add-organisateur-card\">
                            <div class=\"card-body text-center\">
                              <h5 class=\"card-title text-white\"><i class=\"fa fa-plus fa-3x\"></i></h5>
                              <p class=\"card-text text-white mt-2\">Ajouter un organisateur</p>
                            </div>
                          </div>
                        </a>
                      </div>

                      ";
        // line 114
        if ( !Twig\Extension\CoreExtension::testEmpty(CoreExtension::getAttribute($this->env, $this->source, (isset($context["espace"]) || array_key_exists("espace", $context) ? $context["espace"] : (function () { throw new RuntimeError('Variable "espace" does not exist.', 114, $this->source); })()), "organisateurs", [], "any", false, false, false, 114))) {
            // line 115
            yield "                        ";
            $context['_parent'] = $context;
            $context['_seq'] = CoreExtension::ensureTraversable(CoreExtension::getAttribute($this->env, $this->source, (isset($context["espace"]) || array_key_exists("espace", $context) ? $context["espace"] : (function () { throw new RuntimeError('Variable "espace" does not exist.', 115, $this->source); })()), "organisateurs", [], "any", false, false, false, 115));
            foreach ($context['_seq'] as $context["_key"] => $context["organisateur"]) {
                // line 116
                yield "                          <div class=\"col-md-6 mb-4\">
                            <div class=\"card h-100 border-0 shadow-lg\">
                              <div class=\"card-body\">
                                <h5 class=\"card-title\">";
                // line 119
                yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape(CoreExtension::getAttribute($this->env, $this->source, $context["organisateur"], "nom_org", [], "any", false, false, false, 119), "html", null, true);
                yield " ";
                yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape(CoreExtension::getAttribute($this->env, $this->source, $context["organisateur"], "prenom_org", [], "any", false, false, false, 119), "html", null, true);
                yield "</h5>
                                <p><strong>Téléphone :</strong> ";
                // line 120
                yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape(CoreExtension::getAttribute($this->env, $this->source, $context["organisateur"], "telef", [], "any", false, false, false, 120), "html", null, true);
                yield "</p>
                                <p><strong>Description :</strong><br>";
                // line 121
                yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape(CoreExtension::getAttribute($this->env, $this->source, $context["organisateur"], "description_org", [], "any", false, false, false, 121), "html", null, true);
                yield "</p>
                              </div>
                              <div class=\"card-footer d-flex justify-content-between\">
                                <a href=\"";
                // line 124
                yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape($this->extensions['Symfony\Bridge\Twig\Extension\RoutingExtension']->getPath("app_organisateur_edit", ["id_org" => CoreExtension::getAttribute($this->env, $this->source, $context["organisateur"], "id_org", [], "any", false, false, false, 124)]), "html", null, true);
                yield "\" class=\"btn btn-sm btn-outline-primary\">Modifier</a>
                                <form method=\"post\" action=\"";
                // line 125
                yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape($this->extensions['Symfony\Bridge\Twig\Extension\RoutingExtension']->getPath("app_organisateur_delete", ["id_org" => CoreExtension::getAttribute($this->env, $this->source, $context["organisateur"], "id_org", [], "any", false, false, false, 125)]), "html", null, true);
                yield "\" onsubmit=\"return confirm('Êtes-vous sûr ?');\">
                                  <input type=\"hidden\" name=\"_token\" value=\"";
                // line 126
                yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape($this->env->getRuntime('Symfony\Component\Form\FormRenderer')->renderCsrfToken(("delete" . CoreExtension::getAttribute($this->env, $this->source, $context["organisateur"], "id_org", [], "any", false, false, false, 126))), "html", null, true);
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
            // line 133
            yield "                      ";
        } else {
            // line 134
            yield "                        <p class=\"text-muted\">Aucun organisateur assigné à cet espace.</p>
                      ";
        }
        // line 136
        yield "                    </div>
                  </div>
                </div>
              </div>
            </div> <!-- Fin tab-content -->
          </div>
        </div>
      </div>
    </div>
  </div>
</div>

<!-- Footer -->
<footer class=\"footer-no-gap\">
  <div class=\"container\">
    <div class=\"col-lg-12 text-center\">
      <p>Copyright © 2048 Villa Agency Co., Ltd. Design: <a href=\"https://templatemo.com\" target=\"_blank\">TemplateMo</a></p>
    </div>
  </div>
</footer>

<!-- LIVE Script -->
<script>
  document.addEventListener(\"DOMContentLoaded\", function () {
    const liveURL = \"";
        // line 160
        yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape((isset($context["liveURL"]) || array_key_exists("liveURL", $context) ? $context["liveURL"] : (function () { throw new RuntimeError('Variable "liveURL" does not exist.', 160, $this->source); })()), "html", null, true);
        yield "\";
    const container = document.getElementById(\"live-container\");

    fetch(liveURL, { method: \"HEAD\", mode: \"no-cors\" })
      .then(() => {
        container.innerHTML = `
          <div class=\"ratio ratio-16x9\" style=\"border-radius: 12px; overflow: hidden;\">
            <iframe src=\"\${liveURL}\" allowfullscreen frameborder=\"0\"></iframe>
          </div>
        `;
      })
      .catch(() => {
        container.innerHTML = `
          <div class=\"alert alert-warning text-center\" role=\"alert\">
            🚫 Cet espace n’a pas de live en direct disponible pour le moment.
          </div>
        `;
      });
  });
</script>

<!-- Leaflet Map Script -->
<link rel=\"stylesheet\" href=\"https://unpkg.com/leaflet@1.9.4/dist/leaflet.css\" crossorigin=\"\"/>
<script src=\"https://unpkg.com/leaflet@1.9.4/dist/leaflet.js\" crossorigin=\"\"></script>
<script>
  document.addEventListener(\"DOMContentLoaded\", function () {
    var map = L.map('map').setView([";
        // line 186
        yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape((isset($context["latitude"]) || array_key_exists("latitude", $context) ? $context["latitude"] : (function () { throw new RuntimeError('Variable "latitude" does not exist.', 186, $this->source); })()), "html", null, true);
        yield ", ";
        yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape((isset($context["longitude"]) || array_key_exists("longitude", $context) ? $context["longitude"] : (function () { throw new RuntimeError('Variable "longitude" does not exist.', 186, $this->source); })()), "html", null, true);
        yield "], 13);
    L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
      maxZoom: 19,
      attribution: '&copy; OpenStreetMap contributors'
    }).addTo(map);
    L.marker([";
        // line 191
        yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape((isset($context["latitude"]) || array_key_exists("latitude", $context) ? $context["latitude"] : (function () { throw new RuntimeError('Variable "latitude" does not exist.', 191, $this->source); })()), "html", null, true);
        yield ", ";
        yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape((isset($context["longitude"]) || array_key_exists("longitude", $context) ? $context["longitude"] : (function () { throw new RuntimeError('Variable "longitude" does not exist.', 191, $this->source); })()), "html", null, true);
        yield "]).addTo(map)
      .bindPopup(\"";
        // line 192
        yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape(CoreExtension::getAttribute($this->env, $this->source, (isset($context["espace"]) || array_key_exists("espace", $context) ? $context["espace"] : (function () { throw new RuntimeError('Variable "espace" does not exist.', 192, $this->source); })()), "nomEspace", [], "any", false, false, false, 192), "html", null, true);
        yield "\")
      .openPopup();
  });
</script>

<!-- Styles -->
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
        return array (  370 => 192,  364 => 191,  354 => 186,  325 => 160,  299 => 136,  295 => 134,  292 => 133,  279 => 126,  275 => 125,  271 => 124,  265 => 121,  261 => 120,  255 => 119,  250 => 116,  245 => 115,  243 => 114,  230 => 104,  198 => 75,  177 => 57,  172 => 55,  165 => 51,  157 => 46,  153 => 45,  149 => 44,  145 => 43,  141 => 42,  109 => 13,  100 => 6,  87 => 5,  64 => 3,  41 => 1,);
    }

    public function getSourceContext(): Source
    {
        return new Source("{% extends 'base.html.twig' %}

{% block title %}Détails de l'Espace{% endblock %}

{% block body %}
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

      <!-- Onglets -->
      <div class=\"col-lg-12\">
        <div class=\"tabs-content animate__animated animate__fadeInUp\">
          <div class=\"row\">
            <div class=\"nav-wrapper\">
              <ul class=\"nav nav-tabs\" role=\"tablist\">
                <li class=\"nav-item\" role=\"presentation\">
                  <button class=\"nav-link active\" id=\"details-tab\" data-bs-toggle=\"tab\" data-bs-target=\"#details\" type=\"button\" role=\"tab\">Détails</button>
                </li>
                <li class=\"nav-item\" role=\"presentation\">
                  <button class=\"nav-link\" id=\"info-tab\" data-bs-toggle=\"tab\" data-bs-target=\"#info\" type=\"button\" role=\"tab\">🛑 LIVE 🎞️🎥</button>
                </li>
                <li class=\"nav-item\" role=\"presentation\">
                  <button class=\"nav-link\" id=\"organisateurs-tab\" data-bs-toggle=\"tab\" data-bs-target=\"#organisateurs\" type=\"button\" role=\"tab\">Organisateurs</button>
                </li>
              </ul>
            </div>

            <div class=\"tab-content mt-4\" id=\"myTabContent\">
              <!-- Onglet Détails -->
              <div class=\"tab-pane fade show active\" id=\"details\" role=\"tabpanel\">
                <div class=\"row\">
                  <div class=\"col-lg-3\">
                    <div class=\"info-table\">
                      <ul>
                        <li>Adresse <span>{{ espace.adresse }}</span></li>
                        <li>Capacité <span>{{ espace.capacite }}</span></li>
                        <li>Disponibilité <span>{{ espace.disponibilite }}</span></li>
                        <li>Prix <span>{{ espace.prix }} Dt</span></li>
                        <li>Type <span>{{ espace.TypeEspace }}</span></li>
                      </ul>
                    </div>
                  </div>
                  <div class=\"col-lg-6\">
                    <img src=\"{{ asset('uploads/' ~ espace.image) }}\" alt=\"Image de l'espace\" class=\"img-fluid\" style=\"border-radius:8px;\">
                  </div>
                  <div class=\"col-lg-3\">
                    <h4>Détails</h4>
                    <p>{{ espace.description ?? 'Aucune description supplémentaire n\\'est disponible pour cet espace.' }}</p>
                    <div class=\"icon-button mt-3\">
                      <a href=\"{{ path('app_espace_edit', {'idEspace': espace.idEspace}) }}\" class=\"btn btn-primary\">
                        <i class=\"fa fa-calendar\"></i> Modifier l'espace
                      </a>
                    </div>
                  </div>
                </div>

                <!-- Carte Google Maps sans clé API (affichage direct via iframe) -->
<div class=\"col-lg-12 mt-4\">
  <div class=\"animate__animated animate__fadeInUp\">
    <h5>📍 Localisation sur Google Maps</h5>
    <div style=\"height: 400px; border-radius: 12px; overflow: hidden;\">
      <iframe
        width=\"100%\"
        height=\"100%\"
        frameborder=\"0\"
        style=\"border:0;\"
        allowfullscreen
        src=\"https://www.google.com/maps?q={{ espace.adresse | url_encode }}&output=embed\">
      </iframe>
    </div>
  </div>
</div>




              <!-- Onglet LIVE -->
              <div class=\"tab-pane fade\" id=\"info\" role=\"tabpanel\">
                <div class=\"row\">
                  <div class=\"col-lg-12\">
                    <h4>🎥 Vue en Direct</h4>
                    <div id=\"live-container\" class=\"mt-3\" style=\"min-height: 400px;\">
                      <p class=\"text-muted\">🔄 Vérification de la disponibilité du flux live...</p>
                    </div>
                  </div>
                </div>
              </div>

              <!-- Onglet Organisateurs -->
              <div class=\"tab-pane fade\" id=\"organisateurs\" role=\"tabpanel\">
                <div class=\"row\">
                  <div class=\"col-lg-12\">
                    <h4>Organisateurs dans cet espace</h4>
                    <p>Nos organisateurs transforment chaque espace selon vos envies pour une ambiance sur mesure.</p>
                    <div class=\"row mt-4\">
                      <div class=\"col-md-6 mb-4\">
                        <a href=\"{{ path('app_organisateur_new', {'idEspace': espace.idEspace}) }}\" class=\"text-decoration-none\">
                          <div class=\"card h-100 border-0 shadow-lg add-organisateur-card\">
                            <div class=\"card-body text-center\">
                              <h5 class=\"card-title text-white\"><i class=\"fa fa-plus fa-3x\"></i></h5>
                              <p class=\"card-text text-white mt-2\">Ajouter un organisateur</p>
                            </div>
                          </div>
                        </a>
                      </div>

                      {% if espace.organisateurs is not empty %}
                        {% for organisateur in espace.organisateurs %}
                          <div class=\"col-md-6 mb-4\">
                            <div class=\"card h-100 border-0 shadow-lg\">
                              <div class=\"card-body\">
                                <h5 class=\"card-title\">{{ organisateur.nom_org }} {{ organisateur.prenom_org }}</h5>
                                <p><strong>Téléphone :</strong> {{ organisateur.telef }}</p>
                                <p><strong>Description :</strong><br>{{ organisateur.description_org }}</p>
                              </div>
                              <div class=\"card-footer d-flex justify-content-between\">
                                <a href=\"{{ path('app_organisateur_edit', {'id_org': organisateur.id_org}) }}\" class=\"btn btn-sm btn-outline-primary\">Modifier</a>
                                <form method=\"post\" action=\"{{ path('app_organisateur_delete', {'id_org': organisateur.id_org}) }}\" onsubmit=\"return confirm('Êtes-vous sûr ?');\">
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
            </div> <!-- Fin tab-content -->
          </div>
        </div>
      </div>
    </div>
  </div>
</div>

<!-- Footer -->
<footer class=\"footer-no-gap\">
  <div class=\"container\">
    <div class=\"col-lg-12 text-center\">
      <p>Copyright © 2048 Villa Agency Co., Ltd. Design: <a href=\"https://templatemo.com\" target=\"_blank\">TemplateMo</a></p>
    </div>
  </div>
</footer>

<!-- LIVE Script -->
<script>
  document.addEventListener(\"DOMContentLoaded\", function () {
    const liveURL = \"{{ liveURL }}\";
    const container = document.getElementById(\"live-container\");

    fetch(liveURL, { method: \"HEAD\", mode: \"no-cors\" })
      .then(() => {
        container.innerHTML = `
          <div class=\"ratio ratio-16x9\" style=\"border-radius: 12px; overflow: hidden;\">
            <iframe src=\"\${liveURL}\" allowfullscreen frameborder=\"0\"></iframe>
          </div>
        `;
      })
      .catch(() => {
        container.innerHTML = `
          <div class=\"alert alert-warning text-center\" role=\"alert\">
            🚫 Cet espace n’a pas de live en direct disponible pour le moment.
          </div>
        `;
      });
  });
</script>

<!-- Leaflet Map Script -->
<link rel=\"stylesheet\" href=\"https://unpkg.com/leaflet@1.9.4/dist/leaflet.css\" crossorigin=\"\"/>
<script src=\"https://unpkg.com/leaflet@1.9.4/dist/leaflet.js\" crossorigin=\"\"></script>
<script>
  document.addEventListener(\"DOMContentLoaded\", function () {
    var map = L.map('map').setView([{{ latitude }}, {{ longitude }}], 13);
    L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
      maxZoom: 19,
      attribution: '&copy; OpenStreetMap contributors'
    }).addTo(map);
    L.marker([{{ latitude }}, {{ longitude }}]).addTo(map)
      .bindPopup(\"{{ espace.nomEspace }}\")
      .openPopup();
  });
</script>

<!-- Styles -->
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
{% endblock %}", "espace/show.html.twig", "C:\\wamp64\\gestion_espace_symfony\\templates\\espace\\show.html.twig");
    }
}
