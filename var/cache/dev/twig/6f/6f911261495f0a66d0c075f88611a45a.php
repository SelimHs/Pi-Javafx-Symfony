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

/* billet/index.html.twig */
class __TwigTemplate_eb2871166b35352af9c697c2ba5b0d0e extends Template
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
        $__internal_5a27a8ba21ca79b61932376b2fa922d2->enter($__internal_5a27a8ba21ca79b61932376b2fa922d2_prof = new \Twig\Profiler\Profile($this->getTemplateName(), "template", "billet/index.html.twig"));

        $__internal_6f47bbe9983af81f1e7450e9a3e3768f = $this->extensions["Symfony\\Bridge\\Twig\\Extension\\ProfilerExtension"];
        $__internal_6f47bbe9983af81f1e7450e9a3e3768f->enter($__internal_6f47bbe9983af81f1e7450e9a3e3768f_prof = new \Twig\Profiler\Profile($this->getTemplateName(), "template", "billet/index.html.twig"));

        $this->parent = $this->loadTemplate("baseBack.html.twig", "billet/index.html.twig", 1);
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

        yield "Liste des Billets";
        
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
            <i class=\"pe-7s-ticket icon-gradient bg-happy-itmeo\"></i>
          </div>
          <div>Gestion des Billets
            <div class=\"page-title-subheading\">Liste des billets achetés et leurs informations.</div>
          </div>
        </div>
        <div class=\"page-title-actions\">
          <a href=\"";
        // line 19
        yield $this->extensions['Symfony\Bridge\Twig\Extension\RoutingExtension']->getPath("app_billet_new");
        yield "\" class=\"btn btn-success\">
            <i class=\"pe-7s-plus mr-2\"></i> Nouveau Billet
          </a>
        </div>
      </div>
    </div>

    <div class=\"row\">
      <div class=\"col-md-6 col-xl-4\">
        <div class=\"card mb-3 widget-content bg-midnight-bloom\">
          <div class=\"widget-content-wrapper text-white\">
            <div class=\"widget-content-left\">
              <div class=\"widget-heading\">Billets Totaux</div>
              <div class=\"widget-subheading\">Nombre de billets vendus</div>
            </div>
            <div class=\"widget-content-right\">
              <div class=\"widget-numbers text-white\"><span>";
        // line 35
        yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape((isset($context["totalBillets"]) || array_key_exists("totalBillets", $context) ? $context["totalBillets"] : (function () { throw new RuntimeError('Variable "totalBillets" does not exist.', 35, $this->source); })()), "html", null, true);
        yield "</span></div>
            </div>
          </div>
        </div>
      </div>
    
      <div class=\"col-md-6 col-xl-4\">
        <div class=\"card mb-3 widget-content bg-arielle-smile\">
          <div class=\"widget-content-wrapper text-white\">
            <div class=\"widget-content-left\">
              <div class=\"widget-heading\">Billets VIP</div>
              <div class=\"widget-subheading\">Nombre de billets VIP</div>
            </div>
            <div class=\"widget-content-right\">
              <div class=\"widget-numbers text-white\"><span>";
        // line 49
        yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape((isset($context["totalVip"]) || array_key_exists("totalVip", $context) ? $context["totalVip"] : (function () { throw new RuntimeError('Variable "totalVip" does not exist.', 49, $this->source); })()), "html", null, true);
        yield "</span></div>
            </div>
          </div>
        </div>
      </div>
    
      <div class=\"col-md-6 col-xl-4\">
        <div class=\"card mb-3 widget-content bg-grow-early\">
          <div class=\"widget-content-wrapper text-white\">
            <div class=\"widget-content-left\">
              <div class=\"widget-heading\">Billets Duo</div>
              <div class=\"widget-subheading\">Nombre de billets DUO</div>
            </div>
            <div class=\"widget-content-right\">
              <div class=\"widget-numbers text-white\"><span>";
        // line 63
        yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape((isset($context["totalDuo"]) || array_key_exists("totalDuo", $context) ? $context["totalDuo"] : (function () { throw new RuntimeError('Variable "totalDuo" does not exist.', 63, $this->source); })()), "html", null, true);
        yield "</span></div>
            </div>
          </div>
        </div>
      </div>
    </div>

    

    <div class=\"main-card mb-3 card\">
      <div class=\"card-header\">
        Billets enregistrés
      </div>
      <div class=\"table-responsive\">
        <table class=\"align-middle mb-0 table table-striped table-hover\">
          <thead>
            <tr>
              <th class=\"text-center\">#</th>
              <th>Propriétaire</th>
              <th class=\"text-center\">Prix</th>
              <th class=\"text-center\">Date d'achat</th>
              <th class=\"text-center\">Type</th>
              <th class=\"text-center\">Actions</th>
            </tr>
          </thead>
          <tbody>
            ";
        // line 89
        $context['_parent'] = $context;
        $context['_seq'] = CoreExtension::ensureTraversable((isset($context["billets"]) || array_key_exists("billets", $context) ? $context["billets"] : (function () { throw new RuntimeError('Variable "billets" does not exist.', 89, $this->source); })()));
        $context['_iterated'] = false;
        foreach ($context['_seq'] as $context["_key"] => $context["billet"]) {
            // line 90
            yield "              <tr>
                <td class=\"text-center text-muted\">#";
            // line 91
            yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape(CoreExtension::getAttribute($this->env, $this->source, $context["billet"], "idBillet", [], "any", false, false, false, 91), "html", null, true);
            yield "</td>
                <td>
                  <div class=\"widget-content p-0\">
                    <div class=\"widget-content-wrapper\">
                      <div class=\"widget-content-left flex2\">
                        <div class=\"widget-heading\">";
            // line 96
            yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape(CoreExtension::getAttribute($this->env, $this->source, $context["billet"], "proprietaire", [], "any", false, false, false, 96), "html", null, true);
            yield "</div>
                      </div>
                    </div>
                  </div>
                </td>
                <td class=\"text-center\">";
            // line 101
            yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape(CoreExtension::getAttribute($this->env, $this->source, $context["billet"], "prix", [], "any", false, false, false, 101), "html", null, true);
            yield " DT</td>
                <td class=\"text-center\">";
            // line 102
            yield ((CoreExtension::getAttribute($this->env, $this->source, $context["billet"], "dateAchat", [], "any", false, false, false, 102)) ? ($this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape($this->extensions['Twig\Extension\CoreExtension']->formatDate(CoreExtension::getAttribute($this->env, $this->source, $context["billet"], "dateAchat", [], "any", false, false, false, 102), "Y-m-d H:i"), "html", null, true)) : ("—"));
            yield "</td>
                <td class=\"text-center\">
                  <div class=\"badge badge-pill 
                    ";
            // line 105
            if ((CoreExtension::getAttribute($this->env, $this->source, $context["billet"], "type", [], "any", false, false, false, 105) == "VIP")) {
                yield "badge-danger
                    ";
            } elseif ((CoreExtension::getAttribute($this->env, $this->source,             // line 106
$context["billet"], "type", [], "any", false, false, false, 106) == "DUO")) {
                yield "badge-warning
                    ";
            } else {
                // line 107
                yield "badge-info";
            }
            yield "\">
                    ";
            // line 108
            yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape(CoreExtension::getAttribute($this->env, $this->source, $context["billet"], "type", [], "any", false, false, false, 108), "html", null, true);
            yield "
                  </div>
                </td>
                <td class=\"text-center\">
                  <a href=\"";
            // line 112
            yield $this->env->getRuntime('Twig\Runtime\EscaperRuntime')->escape($this->extensions['Symfony\Bridge\Twig\Extension\RoutingExtension']->getPath("app_billet_show", ["idBillet" => CoreExtension::getAttribute($this->env, $this->source, $context["billet"], "idBillet", [], "any", false, false, false, 112)]), "html", null, true);
            yield "\" class=\"btn btn-sm btn-info\">Détails</a>
                </td>
              </tr>
            ";
            $context['_iterated'] = true;
        }
        // line 115
        if (!$context['_iterated']) {
            // line 116
            yield "              <tr>
                <td colspan=\"6\" class=\"text-center text-muted\">Aucun billet trouvé.</td>
              </tr>
            ";
        }
        $_parent = $context['_parent'];
        unset($context['_seq'], $context['_key'], $context['billet'], $context['_parent'], $context['_iterated']);
        $context = array_intersect_key($context, $_parent) + $_parent;
        // line 120
        yield "          </tbody>
        </table>
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
        return "billet/index.html.twig";
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
        return array (  271 => 120,  262 => 116,  260 => 115,  252 => 112,  245 => 108,  240 => 107,  235 => 106,  231 => 105,  225 => 102,  221 => 101,  213 => 96,  205 => 91,  202 => 90,  197 => 89,  168 => 63,  151 => 49,  134 => 35,  115 => 19,  100 => 6,  87 => 5,  64 => 3,  41 => 1,);
    }

    public function getSourceContext(): Source
    {
        return new Source("{% extends 'baseBack.html.twig' %}

{% block title %}Liste des Billets{% endblock %}

{% block body %}
<div class=\"app-main__outer\">
  <div class=\"app-main__inner\">
    <div class=\"app-page-title\">
      <div class=\"page-title-wrapper\">
        <div class=\"page-title-heading\">
          <div class=\"page-title-icon\">
            <i class=\"pe-7s-ticket icon-gradient bg-happy-itmeo\"></i>
          </div>
          <div>Gestion des Billets
            <div class=\"page-title-subheading\">Liste des billets achetés et leurs informations.</div>
          </div>
        </div>
        <div class=\"page-title-actions\">
          <a href=\"{{ path('app_billet_new') }}\" class=\"btn btn-success\">
            <i class=\"pe-7s-plus mr-2\"></i> Nouveau Billet
          </a>
        </div>
      </div>
    </div>

    <div class=\"row\">
      <div class=\"col-md-6 col-xl-4\">
        <div class=\"card mb-3 widget-content bg-midnight-bloom\">
          <div class=\"widget-content-wrapper text-white\">
            <div class=\"widget-content-left\">
              <div class=\"widget-heading\">Billets Totaux</div>
              <div class=\"widget-subheading\">Nombre de billets vendus</div>
            </div>
            <div class=\"widget-content-right\">
              <div class=\"widget-numbers text-white\"><span>{{ totalBillets }}</span></div>
            </div>
          </div>
        </div>
      </div>
    
      <div class=\"col-md-6 col-xl-4\">
        <div class=\"card mb-3 widget-content bg-arielle-smile\">
          <div class=\"widget-content-wrapper text-white\">
            <div class=\"widget-content-left\">
              <div class=\"widget-heading\">Billets VIP</div>
              <div class=\"widget-subheading\">Nombre de billets VIP</div>
            </div>
            <div class=\"widget-content-right\">
              <div class=\"widget-numbers text-white\"><span>{{ totalVip }}</span></div>
            </div>
          </div>
        </div>
      </div>
    
      <div class=\"col-md-6 col-xl-4\">
        <div class=\"card mb-3 widget-content bg-grow-early\">
          <div class=\"widget-content-wrapper text-white\">
            <div class=\"widget-content-left\">
              <div class=\"widget-heading\">Billets Duo</div>
              <div class=\"widget-subheading\">Nombre de billets DUO</div>
            </div>
            <div class=\"widget-content-right\">
              <div class=\"widget-numbers text-white\"><span>{{ totalDuo }}</span></div>
            </div>
          </div>
        </div>
      </div>
    </div>

    

    <div class=\"main-card mb-3 card\">
      <div class=\"card-header\">
        Billets enregistrés
      </div>
      <div class=\"table-responsive\">
        <table class=\"align-middle mb-0 table table-striped table-hover\">
          <thead>
            <tr>
              <th class=\"text-center\">#</th>
              <th>Propriétaire</th>
              <th class=\"text-center\">Prix</th>
              <th class=\"text-center\">Date d'achat</th>
              <th class=\"text-center\">Type</th>
              <th class=\"text-center\">Actions</th>
            </tr>
          </thead>
          <tbody>
            {% for billet in billets %}
              <tr>
                <td class=\"text-center text-muted\">#{{ billet.idBillet }}</td>
                <td>
                  <div class=\"widget-content p-0\">
                    <div class=\"widget-content-wrapper\">
                      <div class=\"widget-content-left flex2\">
                        <div class=\"widget-heading\">{{ billet.proprietaire }}</div>
                      </div>
                    </div>
                  </div>
                </td>
                <td class=\"text-center\">{{ billet.prix }} DT</td>
                <td class=\"text-center\">{{ billet.dateAchat ? billet.dateAchat|date('Y-m-d H:i') : '—' }}</td>
                <td class=\"text-center\">
                  <div class=\"badge badge-pill 
                    {% if billet.type == 'VIP' %}badge-danger
                    {% elseif billet.type == 'DUO' %}badge-warning
                    {% else %}badge-info{% endif %}\">
                    {{ billet.type }}
                  </div>
                </td>
                <td class=\"text-center\">
                  <a href=\"{{ path('app_billet_show', {'idBillet': billet.idBillet}) }}\" class=\"btn btn-sm btn-info\">Détails</a>
                </td>
              </tr>
            {% else %}
              <tr>
                <td colspan=\"6\" class=\"text-center text-muted\">Aucun billet trouvé.</td>
              </tr>
            {% endfor %}
          </tbody>
        </table>
      </div>
    </div>
  </div>
</div>
{% endblock %}
", "billet/index.html.twig", "C:\\wamp64\\gestion_espace_symfony\\templates\\billet\\index.html.twig");
    }
}
