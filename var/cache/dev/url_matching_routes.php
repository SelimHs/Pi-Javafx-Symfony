<?php

/**
 * This file has been auto-generated
 * by the Symfony Routing Component.
 */

return [
    false, // $matchHost
    [ // $staticRoutes
        '/_profiler' => [[['_route' => '_profiler_home', '_controller' => 'web_profiler.controller.profiler::homeAction'], null, null, null, true, false, null]],
        '/_profiler/search' => [[['_route' => '_profiler_search', '_controller' => 'web_profiler.controller.profiler::searchAction'], null, null, null, false, false, null]],
        '/_profiler/search_bar' => [[['_route' => '_profiler_search_bar', '_controller' => 'web_profiler.controller.profiler::searchBarAction'], null, null, null, false, false, null]],
        '/_profiler/phpinfo' => [[['_route' => '_profiler_phpinfo', '_controller' => 'web_profiler.controller.profiler::phpinfoAction'], null, null, null, false, false, null]],
        '/_profiler/xdebug' => [[['_route' => '_profiler_xdebug', '_controller' => 'web_profiler.controller.profiler::xdebugAction'], null, null, null, false, false, null]],
        '/_profiler/open' => [[['_route' => '_profiler_open_file', '_controller' => 'web_profiler.controller.profiler::openAction'], null, null, null, false, false, null]],
        '/billet' => [[['_route' => 'app_billet_index', '_controller' => 'App\\Controller\\BilletController::index'], null, ['GET' => 0], null, false, false, null]],
        '/billet/new' => [[['_route' => 'app_billet_new', '_controller' => 'App\\Controller\\BilletController::newBack'], null, ['GET' => 0, 'POST' => 1], null, false, false, null]],
        '/espace' => [[['_route' => 'app_espace_index', '_controller' => 'App\\Controller\\EspaceController::index'], null, ['GET' => 0], null, false, false, null]],
        '/espace/dashboard' => [[['_route' => 'dashboard_espace_index', '_controller' => 'App\\Controller\\EspaceController::indexBack'], null, ['GET' => 0], null, false, false, null]],
        '/espace/new' => [[['_route' => 'app_espace_new', '_controller' => 'App\\Controller\\EspaceController::new'], null, ['GET' => 0, 'POST' => 1], null, false, false, null]],
        '/espace/newBack' => [[['_route' => 'dashboard_espace_new', '_controller' => 'App\\Controller\\EspaceController::newDashboard'], null, ['GET' => 0, 'POST' => 1], null, false, false, null]],
        '/event' => [[['_route' => 'app_event_index', '_controller' => 'App\\Controller\\EventController::index'], null, ['GET' => 0], null, false, false, null]],
        '/event/dashboard' => [[['_route' => 'dashboard_event_index', '_controller' => 'App\\Controller\\EventController::indexDashboard'], null, ['GET' => 0], null, false, false, null]],
        '/event/new' => [[['_route' => 'app_event_new', '_controller' => 'App\\Controller\\EventController::new'], null, ['GET' => 0, 'POST' => 1], null, false, false, null]],
        '/event/newBack' => [[['_route' => 'dashboard_event_new', '_controller' => 'App\\Controller\\EventController::newDashboard'], null, ['GET' => 0, 'POST' => 1], null, false, false, null]],
        '/fournisseur' => [[['_route' => 'app_fournisseur_index', '_controller' => 'App\\Controller\\FournisseurController::index'], null, ['GET' => 0], null, false, false, null]],
        '/fournisseur/new' => [[['_route' => 'app_fournisseur_new', '_controller' => 'App\\Controller\\FournisseurController::new'], null, ['GET' => 0, 'POST' => 1], null, false, false, null]],
        '/home' => [[['_route' => 'app_home', '_controller' => 'App\\Controller\\HomeController::index'], null, null, null, false, false, null]],
        '/dashboard' => [[['_route' => 'app_dashboard', '_controller' => 'App\\Controller\\HomeController::indexDashboard'], null, null, null, false, false, null]],
        '/organisateur' => [[['_route' => 'app_organisateur_index', '_controller' => 'App\\Controller\\OrganisateurController::index'], null, ['GET' => 0], null, false, false, null]],
        '/organisateur/new' => [[['_route' => 'app_organisateur_new', '_controller' => 'App\\Controller\\OrganisateurController::new'], null, ['GET' => 0, 'POST' => 1], null, false, false, null]],
        '/organisateur/newBack' => [[['_route' => 'dashboard_organisateur_new_general', '_controller' => 'App\\Controller\\OrganisateurController::newBackGeneral'], null, ['GET' => 0, 'POST' => 1], null, false, false, null]],
        '/produit' => [[['_route' => 'app_produit_index', '_controller' => 'App\\Controller\\ProduitController::index'], null, ['GET' => 0], null, false, false, null]],
        '/produit/back' => [[['_route' => 'app_produit_indexback', '_controller' => 'App\\Controller\\ProduitController::indexback'], null, ['GET' => 0], null, false, false, null]],
        '/produit/new' => [[['_route' => 'app_produit_new', '_controller' => 'App\\Controller\\ProduitController::new'], null, ['GET' => 0, 'POST' => 1], null, false, false, null]],
        '/produit/dashboard/new' => [[['_route' => 'dashboard_produit_new', '_controller' => 'App\\Controller\\ProduitController::newBack'], null, ['GET' => 0, 'POST' => 1], null, false, false, null]],
        '/remise' => [[['_route' => 'app_remise_index', '_controller' => 'App\\Controller\\RemiseController::index'], null, ['GET' => 0], null, false, false, null]],
        '/remise/new' => [[['_route' => 'app_remise_new', '_controller' => 'App\\Controller\\RemiseController::new'], null, ['GET' => 0, 'POST' => 1], null, false, false, null]],
        '/reservation' => [[['_route' => 'app_reservation_index', '_controller' => 'App\\Controller\\ReservationController::index'], null, ['GET' => 0], null, false, false, null]],
        '/reservation/new' => [[['_route' => 'app_reservation_new', '_controller' => 'App\\Controller\\ReservationController::new'], null, ['GET' => 0, 'POST' => 1], null, false, false, null]],
    ],
    [ // $regexpList
        0 => '{^(?'
                .'|/_(?'
                    .'|error/(\\d+)(?:\\.([^/]++))?(*:38)'
                    .'|wdt/([^/]++)(*:57)'
                    .'|profiler/(?'
                        .'|font/([^/\\.]++)\\.woff2(*:98)'
                        .'|([^/]++)(?'
                            .'|/(?'
                                .'|search/results(*:134)'
                                .'|router(*:148)'
                                .'|exception(?'
                                    .'|(*:168)'
                                    .'|\\.css(*:181)'
                                .')'
                            .')'
                            .'|(*:191)'
                        .')'
                    .')'
                .')'
                .'|/billet/(?'
                    .'|reservation/([^/]++)(*:233)'
                    .'|([^/]++)(?'
                        .'|(*:252)'
                        .'|/edit(*:265)'
                        .'|(*:273)'
                    .')'
                .')'
                .'|/e(?'
                    .'|space/(?'
                        .'|([^/]++)(*:305)'
                        .'|show/([^/]++)(*:326)'
                        .'|([^/]++)/edit(*:347)'
                        .'|edit/([^/]++)(*:368)'
                        .'|([^/]++)(*:384)'
                        .'|delete/([^/]++)(*:407)'
                    .')'
                    .'|vent/(?'
                        .'|([^/]++)(*:432)'
                        .'|show/([^/]++)(*:453)'
                        .'|([^/]++)/edit(*:474)'
                        .'|edit/([^/]++)(*:495)'
                        .'|delete/([^/]++)(*:518)'
                        .'|([^/]++)/delete(*:541)'
                    .')'
                .')'
                .'|/fournisseur/([^/]++)(?'
                    .'|(*:575)'
                    .'|/edit(*:588)'
                    .'|(*:596)'
                .')'
                .'|/organisateur/(?'
                    .'|newBack/([^/]++)(*:638)'
                    .'|([^/]++)(?'
                        .'|(*:657)'
                        .'|/edit(*:670)'
                        .'|(*:678)'
                    .')'
                    .'|deleteBack/([^/]++)(*:706)'
                    .'|editBack/([^/]++)(*:731)'
                .')'
                .'|/produit/(?'
                    .'|([^/]++)(*:760)'
                    .'|dashboard/([^/]++)(*:786)'
                    .'|([^/]++)/edit(*:807)'
                    .'|dashboard/([^/]++)/edit(*:838)'
                    .'|([^/]++)(*:854)'
                    .'|dashboard/([^/]++)(*:880)'
                .')'
                .'|/re(?'
                    .'|mise/([^/]++)(?'
                        .'|(*:911)'
                        .'|/edit(*:924)'
                        .'|(*:932)'
                    .')'
                    .'|servation/([^/]++)(?'
                        .'|(*:962)'
                        .'|/edit(*:975)'
                        .'|(*:983)'
                    .')'
                .')'
            .')/?$}sDu',
    ],
    [ // $dynamicRoutes
        38 => [[['_route' => '_preview_error', '_controller' => 'error_controller::preview', '_format' => 'html'], ['code', '_format'], null, null, false, true, null]],
        57 => [[['_route' => '_wdt', '_controller' => 'web_profiler.controller.profiler::toolbarAction'], ['token'], null, null, false, true, null]],
        98 => [[['_route' => '_profiler_font', '_controller' => 'web_profiler.controller.profiler::fontAction'], ['fontName'], null, null, false, false, null]],
        134 => [[['_route' => '_profiler_search_results', '_controller' => 'web_profiler.controller.profiler::searchResultsAction'], ['token'], null, null, false, false, null]],
        148 => [[['_route' => '_profiler_router', '_controller' => 'web_profiler.controller.router::panelAction'], ['token'], null, null, false, false, null]],
        168 => [[['_route' => '_profiler_exception', '_controller' => 'web_profiler.controller.exception_panel::body'], ['token'], null, null, false, false, null]],
        181 => [[['_route' => '_profiler_exception_css', '_controller' => 'web_profiler.controller.exception_panel::stylesheet'], ['token'], null, null, false, false, null]],
        191 => [[['_route' => '_profiler', '_controller' => 'web_profiler.controller.profiler::panelAction'], ['token'], null, null, false, true, null]],
        233 => [[['_route' => 'app_billet_reservation', '_controller' => 'App\\Controller\\BilletController::newFront'], ['id'], ['GET' => 0, 'POST' => 1], null, false, true, null]],
        252 => [[['_route' => 'app_billet_show', '_controller' => 'App\\Controller\\BilletController::show'], ['idBillet'], ['GET' => 0], null, false, true, null]],
        265 => [[['_route' => 'app_billet_edit', '_controller' => 'App\\Controller\\BilletController::edit'], ['idBillet'], ['GET' => 0, 'POST' => 1], null, false, false, null]],
        273 => [[['_route' => 'app_billet_delete', '_controller' => 'App\\Controller\\BilletController::delete'], ['idBillet'], ['POST' => 0], null, false, true, null]],
        305 => [[['_route' => 'app_espace_show', '_controller' => 'App\\Controller\\EspaceController::show'], ['idEspace'], ['GET' => 0], null, false, true, null]],
        326 => [[['_route' => 'dashboard_espace_show', '_controller' => 'App\\Controller\\EspaceController::showDashboard'], ['idEspace'], ['GET' => 0], null, false, true, null]],
        347 => [[['_route' => 'app_espace_edit', '_controller' => 'App\\Controller\\EspaceController::edit'], ['idEspace'], ['GET' => 0, 'POST' => 1], null, false, false, null]],
        368 => [[['_route' => 'dashboard_espace_edit', '_controller' => 'App\\Controller\\EspaceController::editDashboard'], ['idEspace'], ['GET' => 0, 'POST' => 1], null, false, true, null]],
        384 => [[['_route' => 'app_espace_delete', '_controller' => 'App\\Controller\\EspaceController::delete'], ['idEspace'], ['POST' => 0], null, false, true, null]],
        407 => [[['_route' => 'dashboard_espace_delete', '_controller' => 'App\\Controller\\EspaceController::deleteDashboard'], ['idEspace'], ['POST' => 0], null, false, true, null]],
        432 => [[['_route' => 'app_event_show', '_controller' => 'App\\Controller\\EventController::show'], ['idEvent'], ['GET' => 0], null, false, true, null]],
        453 => [[['_route' => 'dashboard_event_show', '_controller' => 'App\\Controller\\EventController::showDashboard'], ['idEvent'], ['GET' => 0], null, false, true, null]],
        474 => [[['_route' => 'app_event_edit', '_controller' => 'App\\Controller\\EventController::edit'], ['idEvent'], ['GET' => 0, 'POST' => 1], null, false, false, null]],
        495 => [[['_route' => 'dashboard_event_edit', '_controller' => 'App\\Controller\\EventController::editDasboard'], ['idEvent'], ['GET' => 0, 'POST' => 1], null, false, true, null]],
        518 => [[['_route' => 'app_event_delete', '_controller' => 'App\\Controller\\EventController::delete'], ['idEvent'], ['POST' => 0], null, false, true, null]],
        541 => [[['_route' => 'dashboard_event_delete', '_controller' => 'App\\Controller\\EventController::deleteDashboard'], ['idEvent'], ['POST' => 0], null, false, false, null]],
        575 => [[['_route' => 'app_fournisseur_show', '_controller' => 'App\\Controller\\FournisseurController::show'], ['idFournisseur'], ['GET' => 0], null, false, true, null]],
        588 => [[['_route' => 'app_fournisseur_edit', '_controller' => 'App\\Controller\\FournisseurController::edit'], ['idFournisseur'], ['GET' => 0, 'POST' => 1], null, false, false, null]],
        596 => [[['_route' => 'app_fournisseur_delete', '_controller' => 'App\\Controller\\FournisseurController::delete'], ['idFournisseur'], ['POST' => 0], null, false, true, null]],
        638 => [[['_route' => 'dashboard_organisateur_new', '_controller' => 'App\\Controller\\OrganisateurController::newBack'], ['idEspace'], ['GET' => 0, 'POST' => 1], null, false, true, null]],
        657 => [[['_route' => 'app_organisateur_show', '_controller' => 'App\\Controller\\OrganisateurController::show'], ['id_org'], ['GET' => 0], null, false, true, null]],
        670 => [[['_route' => 'app_organisateur_edit', '_controller' => 'App\\Controller\\OrganisateurController::edit'], ['id_org'], ['GET' => 0, 'POST' => 1], null, false, false, null]],
        678 => [[['_route' => 'app_organisateur_delete', '_controller' => 'App\\Controller\\OrganisateurController::delete'], ['id_org'], ['POST' => 0], null, false, true, null]],
        706 => [[['_route' => 'dashboard_organisateur_delete', '_controller' => 'App\\Controller\\OrganisateurController::deleteBack'], ['id_org'], ['POST' => 0], null, false, true, null]],
        731 => [[['_route' => 'dashboard_organisateur_edit', '_controller' => 'App\\Controller\\OrganisateurController::editBack'], ['id_org'], ['GET' => 0, 'POST' => 1], null, false, true, null]],
        760 => [[['_route' => 'app_produit_show', '_controller' => 'App\\Controller\\ProduitController::show'], ['idProduit'], ['GET' => 0], null, false, true, null]],
        786 => [[['_route' => 'dashboard_produit_show', '_controller' => 'App\\Controller\\ProduitController::showBack'], ['idProduit'], ['GET' => 0], null, false, true, null]],
        807 => [[['_route' => 'app_produit_edit', '_controller' => 'App\\Controller\\ProduitController::edit'], ['idProduit'], ['GET' => 0, 'POST' => 1], null, false, false, null]],
        838 => [[['_route' => 'dashboard_produit_edit', '_controller' => 'App\\Controller\\ProduitController::editBack'], ['idProduit'], ['GET' => 0, 'POST' => 1], null, false, false, null]],
        854 => [[['_route' => 'app_produit_delete', '_controller' => 'App\\Controller\\ProduitController::delete'], ['idProduit'], ['POST' => 0], null, false, true, null]],
        880 => [[['_route' => 'dashboard_produit_delete', '_controller' => 'App\\Controller\\ProduitController::deleteBack'], ['idProduit'], ['POST' => 0], null, false, true, null]],
        911 => [[['_route' => 'app_remise_show', '_controller' => 'App\\Controller\\RemiseController::show'], ['idRemise'], ['GET' => 0], null, false, true, null]],
        924 => [[['_route' => 'app_remise_edit', '_controller' => 'App\\Controller\\RemiseController::edit'], ['idRemise'], ['GET' => 0, 'POST' => 1], null, false, false, null]],
        932 => [[['_route' => 'app_remise_delete', '_controller' => 'App\\Controller\\RemiseController::delete'], ['idRemise'], ['POST' => 0], null, false, true, null]],
        962 => [[['_route' => 'app_reservation_show', '_controller' => 'App\\Controller\\ReservationController::show'], ['idReservation'], ['GET' => 0], null, false, true, null]],
        975 => [[['_route' => 'app_reservation_edit', '_controller' => 'App\\Controller\\ReservationController::edit'], ['idReservation'], ['GET' => 0, 'POST' => 1], null, false, false, null]],
        983 => [
            [['_route' => 'app_reservation_delete', '_controller' => 'App\\Controller\\ReservationController::delete'], ['idReservation'], ['POST' => 0], null, false, true, null],
            [null, null, null, null, false, false, 0],
        ],
    ],
    null, // $checkCondition
];
