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

/* espace/showBack.html.twig */
class __TwigTemplate_9150d371203e54e046b6e8513aae7fd0 extends Template
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
        $__internal_5a27a8ba21ca79b61932376b2fa922d2->enter($__internal_5a27a8ba21ca79b61932376b2fa922d2_prof = new \Twig\Profiler\Profile($this->getTemplateName(), "template", "espace/showBack.html.twig"));

        $__internal_6f47bbe9983af81f1e7450e9a3e3768f = $this->extensions["Symfony\\Bridge\\Twig\\Extension\\ProfilerExtension"];
        $__internal_6f47bbe9983af81f1e7450e9a3e3768f->enter($__internal_6f47bbe9983af81f1e7450e9a3e3768f_prof = new \Twig\Profiler\Profile($this->getTemplateName(), "template", "espace/showBack.html.twig"));

        $this->parent = $this->loadTemplate("baseBack.html.twig", "espace/showBack.html.twig", 1);
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
        yield "<div class=\"app-main__outer\">
  <div class=\"app-main__inner\">

    <!-- Page Title -->
    <div class=\"app-page-title\">
      <div class=\"page-title-wrapper\">
        <div class=\"page-title-heading\">
          <div class=\"page-title-icon\">
            <i class=\"pe-7s-map-marker icon-gradient bg-amy-crisp\"></i>
          </div>
          <div>
            Détails de l'Espace
            <div class=\"page-title-subheading\">Aperçu complet de l’espace sélectionné.</div>
          </div>
        </div>
        <div class=\"page-title-actions\">
          <a href=\"";
        // line 22
        yield $this->extensions['Symfony\Bridge\Twig\Extension\RoutingExtension']->getPath("dashboard_espace_index");
        yield "\" class=\"btn btn-outline-secondary mr-2\">
            <i class=\"pe-7s-back\"></i> Retour
          </a>
          <a href=\"";
        // line 25
        yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape($this->extensions['Symfony\Bridge\Twig\Extension\RoutingExtension']->getPath("dashboard_espace_edit", ["idEspace" => CoreExtension::getAttribute($this->env, $this->source, (isset($context["espace"]) || array_key_exists("espace", $context) ? $context["espace"] : (function () { throw new RuntimeError('Variable "espace" does not exist.', 25, $this->source); })()), "idEspace", [], "any", false, false, false, 25)]), "html", null, true);
        yield "\" class=\"btn btn-warning mr-2\">
            <i class=\"pe-7s-note\"></i>
          </a>
          <form method=\"post\" action=\"";
        // line 28
        yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape($this->extensions['Symfony\Bridge\Twig\Extension\RoutingExtension']->getPath("dashboard_espace_delete", ["idEspace" => CoreExtension::getAttribute($this->env, $this->source, (isset($context["espace"]) || array_key_exists("espace", $context) ? $context["espace"] : (function () { throw new RuntimeError('Variable "espace" does not exist.', 28, $this->source); })()), "idEspace", [], "any", false, false, false, 28)]), "html", null, true);
        yield "\"
                onsubmit=\"return confirm('Êtes-vous sûr de vouloir supprimer cet espace ?');\" style=\"display: inline;\">
            <input type=\"hidden\" name=\"_token\" value=\"";
        // line 30
        yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape($this->env->getRuntime('Symfony\Component\Form\FormRenderer')->renderCsrfToken(("delete" . CoreExtension::getAttribute($this->env, $this->source, (isset($context["espace"]) || array_key_exists("espace", $context) ? $context["espace"] : (function () { throw new RuntimeError('Variable "espace" does not exist.', 30, $this->source); })()), "idEspace", [], "any", false, false, false, 30))), "html", null, true);
        yield "\">
            <button class=\"btn btn-danger\" title=\"Supprimer\">
              <i class=\"pe-7s-trash\"></i>
            </button>
          </form>
        </div>
      </div>
    </div>

    <!-- Espace Image -->
    <div class=\"mb-4 text-center\">
      <img 
        src=\"";
        // line 42
        yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape($this->extensions['Symfony\Bridge\Twig\Extension\AssetExtension']->getAssetUrl(("uploads/" . (( !Twig\Extension\CoreExtension::testEmpty(CoreExtension::getAttribute($this->env, $this->source, (isset($context["espace"]) || array_key_exists("espace", $context) ? $context["espace"] : (function () { throw new RuntimeError('Variable "espace" does not exist.', 42, $this->source); })()), "image", [], "any", false, false, false, 42))) ? (CoreExtension::getAttribute($this->env, $this->source, (isset($context["espace"]) || array_key_exists("espace", $context) ? $context["espace"] : (function () { throw new RuntimeError('Variable "espace" does not exist.', 42, $this->source); })()), "image", [], "any", false, false, false, 42)) : ("exemple.jpg")))), "html", null, true);
        yield "\" 
        class=\"img-fluid rounded shadow\"
        alt=\"";
        // line 44
        yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape(CoreExtension::getAttribute($this->env, $this->source, (isset($context["espace"]) || array_key_exists("espace", $context) ? $context["espace"] : (function () { throw new RuntimeError('Variable "espace" does not exist.', 44, $this->source); })()), "nomEspace", [], "any", false, false, false, 44), "html", null, true);
        yield "\"
        style=\"max-height: 300px;\"
      >
    </div>

    <!-- Content Card -->
    <div class=\"row\">
      <div class=\"col-md-8 offset-md-2\">
        <div class=\"main-card mb-3 card\">
          <div class=\"card-body\">
            <h5 class=\"card-title\">Informations de l'Espace</h5>
            <table class=\"mb-0 table table-bordered table-striped\">
              <tbody>
                <tr>
                  <th>ID</th>
                  <td>";
        // line 59
        yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape(CoreExtension::getAttribute($this->env, $this->source, (isset($context["espace"]) || array_key_exists("espace", $context) ? $context["espace"] : (function () { throw new RuntimeError('Variable "espace" does not exist.', 59, $this->source); })()), "idEspace", [], "any", false, false, false, 59), "html", null, true);
        yield "</td>
                </tr>
                <tr>
                  <th>Nom</th>
                  <td>";
        // line 63
        yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape(CoreExtension::getAttribute($this->env, $this->source, (isset($context["espace"]) || array_key_exists("espace", $context) ? $context["espace"] : (function () { throw new RuntimeError('Variable "espace" does not exist.', 63, $this->source); })()), "nomEspace", [], "any", false, false, false, 63), "html", null, true);
        yield "</td>
                </tr>
                <tr>
                  <th>Adresse</th>
                  <td>";
        // line 67
        yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape(CoreExtension::getAttribute($this->env, $this->source, (isset($context["espace"]) || array_key_exists("espace", $context) ? $context["espace"] : (function () { throw new RuntimeError('Variable "espace" does not exist.', 67, $this->source); })()), "adresse", [], "any", false, false, false, 67), "html", null, true);
        yield "</td>
                </tr>
                <tr>
                  <th>Capacité</th>
                  <td>";
        // line 71
        yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape(CoreExtension::getAttribute($this->env, $this->source, (isset($context["espace"]) || array_key_exists("espace", $context) ? $context["espace"] : (function () { throw new RuntimeError('Variable "espace" does not exist.', 71, $this->source); })()), "capacite", [], "any", false, false, false, 71), "html", null, true);
        yield "</td>
                </tr>
                <tr>
                  <th>Disponibilité</th>
                  <td>";
        // line 75
        yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape(CoreExtension::getAttribute($this->env, $this->source, (isset($context["espace"]) || array_key_exists("espace", $context) ? $context["espace"] : (function () { throw new RuntimeError('Variable "espace" does not exist.', 75, $this->source); })()), "disponibilite", [], "any", false, false, false, 75), "html", null, true);
        yield "</td>
                </tr>
                <tr>
                  <th>Prix</th>
                  <td>";
        // line 79
        yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape(CoreExtension::getAttribute($this->env, $this->source, (isset($context["espace"]) || array_key_exists("espace", $context) ? $context["espace"] : (function () { throw new RuntimeError('Variable "espace" does not exist.', 79, $this->source); })()), "prix", [], "any", false, false, false, 79), "html", null, true);
        yield " DT</td>
                </tr>
                <tr>
                  <th>Type d'espace</th>
                  <td>";
        // line 83
        yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape(CoreExtension::getAttribute($this->env, $this->source, (isset($context["espace"]) || array_key_exists("espace", $context) ? $context["espace"] : (function () { throw new RuntimeError('Variable "espace" does not exist.', 83, $this->source); })()), "TypeEspace", [], "any", false, false, false, 83), "html", null, true);
        yield "</td>
                </tr>
              </tbody>
            </table>
          </div>
        </div>
      </div>
      <!-- ORGANISATEURS DE L'ESPACE -->
<div class=\"row mt-5\">
  <div class=\"col-12 d-flex justify-content-between align-items-center mb-3\">
    <h4 class=\"mb-0\">Organisateurs liés</h4>
    <a href=\"";
        // line 94
        yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape($this->extensions['Symfony\Bridge\Twig\Extension\RoutingExtension']->getPath("dashboard_organisateur_new", ["idEspace" => CoreExtension::getAttribute($this->env, $this->source, (isset($context["espace"]) || array_key_exists("espace", $context) ? $context["espace"] : (function () { throw new RuntimeError('Variable "espace" does not exist.', 94, $this->source); })()), "idEspace", [], "any", false, false, false, 94)]), "html", null, true);
        yield "\" class=\"btn btn-success btn-sm\">
  <i class=\"pe-7s-plus\"></i> Ajouter Organisateur
</a>

  </div>

  ";
        // line 100
        if ( !Twig\Extension\CoreExtension::testEmpty(CoreExtension::getAttribute($this->env, $this->source, (isset($context["espace"]) || array_key_exists("espace", $context) ? $context["espace"] : (function () { throw new RuntimeError('Variable "espace" does not exist.', 100, $this->source); })()), "organisateurs", [], "any", false, false, false, 100))) {
            // line 101
            yield "    ";
            $context['_parent'] = $context;
            $context['_seq'] = CoreExtension::ensureTraversable(CoreExtension::getAttribute($this->env, $this->source, (isset($context["espace"]) || array_key_exists("espace", $context) ? $context["espace"] : (function () { throw new RuntimeError('Variable "espace" does not exist.', 101, $this->source); })()), "organisateurs", [], "any", false, false, false, 101));
            foreach ($context['_seq'] as $context["_key"] => $context["organisateur"]) {
                // line 102
                yield "      <div class=\"col-md-6 col-xl-4 mb-4\">
        <div class=\"card shadow-sm border-0\">
          <div class=\"card-body\">
            <h5 class=\"card-title text-primary text-uppercase\">
              ";
                // line 106
                yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape(CoreExtension::getAttribute($this->env, $this->source, $context["organisateur"], "nomOrg", [], "any", false, false, false, 106), "html", null, true);
                yield " ";
                yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape(CoreExtension::getAttribute($this->env, $this->source, $context["organisateur"], "prenomOrg", [], "any", false, false, false, 106), "html", null, true);
                yield "
            </h5>
            <p class=\"mb-1\">
              <i class=\"pe-7s-call text-muted me-2\"></i><strong>Téléphone :</strong> ";
                // line 109
                yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape(CoreExtension::getAttribute($this->env, $this->source, $context["organisateur"], "telef", [], "any", false, false, false, 109), "html", null, true);
                yield "
            </p>
            <p class=\"mb-2\">
              <i class=\"pe-7s-note2 text-muted me-2\"></i><strong>Description :</strong><br>
              <span class=\"text-muted\">";
                // line 113
                yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape(CoreExtension::getAttribute($this->env, $this->source, $context["organisateur"], "descriptionOrg", [], "any", false, false, false, 113), "html", null, true);
                yield "</span>
            </p>

            <div class=\"d-flex justify-content-end\">
              <a href=\"";
                // line 117
                yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape($this->extensions['Symfony\Bridge\Twig\Extension\RoutingExtension']->getPath("dashboard_organisateur_edit", ["id_org" => CoreExtension::getAttribute($this->env, $this->source, $context["organisateur"], "id_org", [], "any", false, false, false, 117)]), "html", null, true);
                yield "\"
                 class=\"btn btn-sm btn-outline-warning me-2\" title=\"Modifier\">
                <i class=\"pe-7s-pen\"></i>
              </a>
              <form method=\"post\"
                    action=\"";
                // line 122
                yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape($this->extensions['Symfony\Bridge\Twig\Extension\RoutingExtension']->getPath("dashboard_organisateur_delete", ["id_org" => CoreExtension::getAttribute($this->env, $this->source, $context["organisateur"], "id_org", [], "any", false, false, false, 122)]), "html", null, true);
                yield "\"
                    onsubmit=\"return confirm('Voulez-vous supprimer cet organisateur ?');\">
                <input type=\"hidden\" name=\"_token\" value=\"";
                // line 124
                yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape($this->env->getRuntime('Symfony\Component\Form\FormRenderer')->renderCsrfToken(("delete" . CoreExtension::getAttribute($this->env, $this->source, $context["organisateur"], "id_org", [], "any", false, false, false, 124))), "html", null, true);
                yield "\">
                <button class=\"btn btn-sm btn-outline-danger\" title=\"Supprimer\">
                  <i class=\"pe-7s-trash\"></i>
                </button>
              </form>
            </div>
          </div>
        </div>
      </div>
    ";
            }
            $_parent = $context['_parent'];
            unset($context['_seq'], $context['_key'], $context['organisateur'], $context['_parent']);
            $context = array_intersect_key($context, $_parent) + $_parent;
            // line 134
            yield "  ";
        } else {
            // line 135
            yield "    <div class=\"col-12\">
      <div class=\"alert alert-info text-center\">
        Aucun organisateur assigné à cet espace.
      </div>
    </div>
  ";
        }
        // line 141
        yield "</div>

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
        return "espace/showBack.html.twig";
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
        return array (  313 => 141,  305 => 135,  302 => 134,  286 => 124,  281 => 122,  273 => 117,  266 => 113,  259 => 109,  251 => 106,  245 => 102,  240 => 101,  238 => 100,  229 => 94,  215 => 83,  208 => 79,  201 => 75,  194 => 71,  187 => 67,  180 => 63,  173 => 59,  155 => 44,  150 => 42,  135 => 30,  130 => 28,  124 => 25,  118 => 22,  100 => 6,  87 => 5,  64 => 3,  41 => 1,);
    }

    public function getSourceContext(): Source
    {
        return new Source("{% extends 'baseBack.html.twig' %}

{% block title %}Détails de l'Espace{% endblock %}

{% block body %}
<div class=\"app-main__outer\">
  <div class=\"app-main__inner\">

    <!-- Page Title -->
    <div class=\"app-page-title\">
      <div class=\"page-title-wrapper\">
        <div class=\"page-title-heading\">
          <div class=\"page-title-icon\">
            <i class=\"pe-7s-map-marker icon-gradient bg-amy-crisp\"></i>
          </div>
          <div>
            Détails de l'Espace
            <div class=\"page-title-subheading\">Aperçu complet de l’espace sélectionné.</div>
          </div>
        </div>
        <div class=\"page-title-actions\">
          <a href=\"{{ path('dashboard_espace_index') }}\" class=\"btn btn-outline-secondary mr-2\">
            <i class=\"pe-7s-back\"></i> Retour
          </a>
          <a href=\"{{ path('dashboard_espace_edit', {'idEspace': espace.idEspace}) }}\" class=\"btn btn-warning mr-2\">
            <i class=\"pe-7s-note\"></i>
          </a>
          <form method=\"post\" action=\"{{ path('dashboard_espace_delete', {'idEspace': espace.idEspace}) }}\"
                onsubmit=\"return confirm('Êtes-vous sûr de vouloir supprimer cet espace ?');\" style=\"display: inline;\">
            <input type=\"hidden\" name=\"_token\" value=\"{{ csrf_token('delete' ~ espace.idEspace) }}\">
            <button class=\"btn btn-danger\" title=\"Supprimer\">
              <i class=\"pe-7s-trash\"></i>
            </button>
          </form>
        </div>
      </div>
    </div>

    <!-- Espace Image -->
    <div class=\"mb-4 text-center\">
      <img 
        src=\"{{ asset('uploads/' ~ (espace.image is not empty ? espace.image : 'exemple.jpg')) }}\" 
        class=\"img-fluid rounded shadow\"
        alt=\"{{ espace.nomEspace }}\"
        style=\"max-height: 300px;\"
      >
    </div>

    <!-- Content Card -->
    <div class=\"row\">
      <div class=\"col-md-8 offset-md-2\">
        <div class=\"main-card mb-3 card\">
          <div class=\"card-body\">
            <h5 class=\"card-title\">Informations de l'Espace</h5>
            <table class=\"mb-0 table table-bordered table-striped\">
              <tbody>
                <tr>
                  <th>ID</th>
                  <td>{{ espace.idEspace }}</td>
                </tr>
                <tr>
                  <th>Nom</th>
                  <td>{{ espace.nomEspace }}</td>
                </tr>
                <tr>
                  <th>Adresse</th>
                  <td>{{ espace.adresse }}</td>
                </tr>
                <tr>
                  <th>Capacité</th>
                  <td>{{ espace.capacite }}</td>
                </tr>
                <tr>
                  <th>Disponibilité</th>
                  <td>{{ espace.disponibilite }}</td>
                </tr>
                <tr>
                  <th>Prix</th>
                  <td>{{ espace.prix }} DT</td>
                </tr>
                <tr>
                  <th>Type d'espace</th>
                  <td>{{ espace.TypeEspace }}</td>
                </tr>
              </tbody>
            </table>
          </div>
        </div>
      </div>
      <!-- ORGANISATEURS DE L'ESPACE -->
<div class=\"row mt-5\">
  <div class=\"col-12 d-flex justify-content-between align-items-center mb-3\">
    <h4 class=\"mb-0\">Organisateurs liés</h4>
    <a href=\"{{ path('dashboard_organisateur_new', { 'idEspace': espace.idEspace }) }}\" class=\"btn btn-success btn-sm\">
  <i class=\"pe-7s-plus\"></i> Ajouter Organisateur
</a>

  </div>

  {% if espace.organisateurs is not empty %}
    {% for organisateur in espace.organisateurs %}
      <div class=\"col-md-6 col-xl-4 mb-4\">
        <div class=\"card shadow-sm border-0\">
          <div class=\"card-body\">
            <h5 class=\"card-title text-primary text-uppercase\">
              {{ organisateur.nomOrg }} {{ organisateur.prenomOrg }}
            </h5>
            <p class=\"mb-1\">
              <i class=\"pe-7s-call text-muted me-2\"></i><strong>Téléphone :</strong> {{ organisateur.telef }}
            </p>
            <p class=\"mb-2\">
              <i class=\"pe-7s-note2 text-muted me-2\"></i><strong>Description :</strong><br>
              <span class=\"text-muted\">{{ organisateur.descriptionOrg }}</span>
            </p>

            <div class=\"d-flex justify-content-end\">
              <a href=\"{{ path('dashboard_organisateur_edit', { 'id_org': organisateur.id_org }) }}\"
                 class=\"btn btn-sm btn-outline-warning me-2\" title=\"Modifier\">
                <i class=\"pe-7s-pen\"></i>
              </a>
              <form method=\"post\"
                    action=\"{{ path('dashboard_organisateur_delete', { 'id_org': organisateur.id_org }) }}\"
                    onsubmit=\"return confirm('Voulez-vous supprimer cet organisateur ?');\">
                <input type=\"hidden\" name=\"_token\" value=\"{{ csrf_token('delete' ~ organisateur.id_org) }}\">
                <button class=\"btn btn-sm btn-outline-danger\" title=\"Supprimer\">
                  <i class=\"pe-7s-trash\"></i>
                </button>
              </form>
            </div>
          </div>
        </div>
      </div>
    {% endfor %}
  {% else %}
    <div class=\"col-12\">
      <div class=\"alert alert-info text-center\">
        Aucun organisateur assigné à cet espace.
      </div>
    </div>
  {% endif %}
</div>

    </div>
    

  </div>
</div>
{% endblock %}
", "espace/showBack.html.twig", "C:\\wamp64\\gestion_espace_symfony\\templates\\espace\\showBack.html.twig");
    }
}
