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
        '/billet/new' => [[['_route' => 'app_billet_new', '_controller' => 'App\\Controller\\BilletController::new'], null, ['GET' => 0, 'POST' => 1], null, false, false, null]],
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
                .'|/billet/([^/]++)(?'
                    .'|(*:221)'
                    .'|/edit(*:234)'
                    .'|(*:242)'
                .')'
                .'|/e(?'
                    .'|space/(?'
                        .'|([^/]++)(*:273)'
                        .'|show/([^/]++)(*:294)'
                        .'|([^/]++)/edit(*:315)'
                        .'|edit/([^/]++)(*:336)'
                        .'|([^/]++)(*:352)'
                        .'|delete/([^/]++)(*:375)'
                    .')'
                    .'|vent/(?'
                        .'|([^/]++)(*:400)'
                        .'|show/([^/]++)(*:421)'
                        .'|([^/]++)/edit(*:442)'
                        .'|edit/([^/]++)(*:463)'
                        .'|delete/([^/]++)(*:486)'
                        .'|([^/]++)/delete(*:509)'
                    .')'
                .')'
                .'|/fournisseur/([^/]++)(?'
                    .'|(*:543)'
                    .'|/edit(*:556)'
                    .'|(*:564)'
                .')'
                .'|/organisateur/(?'
                    .'|newBack/([^/]++)(*:606)'
                    .'|([^/]++)(?'
                        .'|(*:625)'
                        .'|/edit(*:638)'
                        .'|(*:646)'
                    .')'
                    .'|deleteBack/([^/]++)(*:674)'
                    .'|editBack/([^/]++)(*:699)'
                .')'
                .'|/produit/(?'
                    .'|([^/]++)(*:728)'
                    .'|dashboard/([^/]++)(*:754)'
                    .'|([^/]++)/edit(*:775)'
                    .'|dashboard/([^/]++)/edit(*:806)'
                    .'|([^/]++)(*:822)'
                    .'|dashboard/([^/]++)(*:848)'
                .')'
                .'|/re(?'
                    .'|mise/([^/]++)(?'
                        .'|(*:879)'
                        .'|/edit(*:892)'
                        .'|(*:900)'
                    .')'
                    .'|servation/([^/]++)(?'
                        .'|(*:930)'
                        .'|/edit(*:943)'
                        .'|(*:951)'
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
        221 => [[['_route' => 'app_billet_show', '_controller' => 'App\\Controller\\BilletController::show'], ['idBillet'], ['GET' => 0], null, false, true, null]],
        234 => [[['_route' => 'app_billet_edit', '_controller' => 'App\\Controller\\BilletController::edit'], ['idBillet'], ['GET' => 0, 'POST' => 1], null, false, false, null]],
        242 => [[['_route' => 'app_billet_delete', '_controller' => 'App\\Controller\\BilletController::delete'], ['idBillet'], ['POST' => 0], null, false, true, null]],
        273 => [[['_route' => 'app_espace_show', '_controller' => 'App\\Controller\\EspaceController::show'], ['idEspace'], ['GET' => 0], null, false, true, null]],
        294 => [[['_route' => 'dashboard_espace_show', '_controller' => 'App\\Controller\\EspaceController::showDashboard'], ['idEspace'], ['GET' => 0], null, false, true, null]],
        315 => [[['_route' => 'app_espace_edit', '_controller' => 'App\\Controller\\EspaceController::edit'], ['idEspace'], ['GET' => 0, 'POST' => 1], null, false, false, null]],
        336 => [[['_route' => 'dashboard_espace_edit', '_controller' => 'App\\Controller\\EspaceController::editDashboard'], ['idEspace'], ['GET' => 0, 'POST' => 1], null, false, true, null]],
        352 => [[['_route' => 'app_espace_delete', '_controller' => 'App\\Controller\\EspaceController::delete'], ['idEspace'], ['POST' => 0], null, false, true, null]],
        375 => [[['_route' => 'dashboard_espace_delete', '_controller' => 'App\\Controller\\EspaceController::deleteDashboard'], ['idEspace'], ['POST' => 0], null, false, true, null]],
        400 => [[['_route' => 'app_event_show', '_controller' => 'App\\Controller\\EventController::show'], ['idEvent'], ['GET' => 0], null, false, true, null]],
        421 => [[['_route' => 'dashboard_event_show', '_controller' => 'App\\Controller\\EventController::showDashboard'], ['idEvent'], ['GET' => 0], null, false, true, null]],
        442 => [[['_route' => 'app_event_edit', '_controller' => 'App\\Controller\\EventController::edit'], ['idEvent'], ['GET' => 0, 'POST' => 1], null, false, false, null]],
        463 => [[['_route' => 'dashboard_event_edit', '_controller' => 'App\\Controller\\EventController::editDasboard'], ['idEvent'], ['GET' => 0, 'POST' => 1], null, false, true, null]],
        486 => [[['_route' => 'app_event_delete', '_controller' => 'App\\Controller\\EventController::delete'], ['idEvent'], ['POST' => 0], null, false, true, null]],
        509 => [[['_route' => 'dashboard_event_delete', '_controller' => 'App\\Controller\\EventController::deleteDashboard'], ['idEvent'], ['POST' => 0], null, false, false, null]],
        543 => [[['_route' => 'app_fournisseur_show', '_controller' => 'App\\Controller\\FournisseurController::show'], ['idFournisseur'], ['GET' => 0], null, false, true, null]],
        556 => [[['_route' => 'app_fournisseur_edit', '_controller' => 'App\\Controller\\FournisseurController::edit'], ['idFournisseur'], ['GET' => 0, 'POST' => 1], null, false, false, null]],
        564 => [[['_route' => 'app_fournisseur_delete', '_controller' => 'App\\Controller\\FournisseurController::delete'], ['idFournisseur'], ['POST' => 0], null, false, true, null]],
        606 => [[['_route' => 'dashboard_organisateur_new', '_controller' => 'App\\Controller\\OrganisateurController::newBack'], ['idEspace'], ['GET' => 0, 'POST' => 1], null, false, true, null]],
        625 => [[['_route' => 'app_organisateur_show', '_controller' => 'App\\Controller\\OrganisateurController::show'], ['id_org'], ['GET' => 0], null, false, true, null]],
        638 => [[['_route' => 'app_organisateur_edit', '_controller' => 'App\\Controller\\OrganisateurController::edit'], ['id_org'], ['GET' => 0, 'POST' => 1], null, false, false, null]],
        646 => [[['_route' => 'app_organisateur_delete', '_controller' => 'App\\Controller\\OrganisateurController::delete'], ['id_org'], ['POST' => 0], null, false, true, null]],
        674 => [[['_route' => 'dashboard_organisateur_delete', '_controller' => 'App\\Controller\\OrganisateurController::deleteBack'], ['id_org'], ['POST' => 0], null, false, true, null]],
        699 => [[['_route' => 'dashboard_organisateur_edit', '_controller' => 'App\\Controller\\OrganisateurController::editBack'], ['id_org'], ['GET' => 0, 'POST' => 1], null, false, true, null]],
        728 => [[['_route' => 'app_produit_show', '_controller' => 'App\\Controller\\ProduitController::show'], ['idProduit'], ['GET' => 0], null, false, true, null]],
        754 => [[['_route' => 'dashboard_produit_show', '_controller' => 'App\\Controller\\ProduitController::showBack'], ['idProduit'], ['GET' => 0], null, false, true, null]],
        775 => [[['_route' => 'app_produit_edit', '_controller' => 'App\\Controller\\ProduitController::edit'], ['idProduit'], ['GET' => 0, 'POST' => 1], null, false, false, null]],
        806 => [[['_route' => 'dashboard_produit_edit', '_controller' => 'App\\Controller\\ProduitController::editBack'], ['idProduit'], ['GET' => 0, 'POST' => 1], null, false, false, null]],
        822 => [[['_route' => 'app_produit_delete', '_controller' => 'App\\Controller\\ProduitController::delete'], ['idProduit'], ['POST' => 0], null, false, true, null]],
        848 => [[['_route' => 'dashboard_produit_delete', '_controller' => 'App\\Controller\\ProduitController::deleteBack'], ['idProduit'], ['POST' => 0], null, false, true, null]],
        879 => [[['_route' => 'app_remise_show', '_controller' => 'App\\Controller\\RemiseController::show'], ['idRemise'], ['GET' => 0], null, false, true, null]],
        892 => [[['_route' => 'app_remise_edit', '_controller' => 'App\\Controller\\RemiseController::edit'], ['idRemise'], ['GET' => 0, 'POST' => 1], null, false, false, null]],
        900 => [[['_route' => 'app_remise_delete', '_controller' => 'App\\Controller\\RemiseController::delete'], ['idRemise'], ['POST' => 0], null, false, true, null]],
        930 => [[['_route' => 'app_reservation_show', '_controller' => 'App\\Controller\\ReservationController::show'], ['idReservation'], ['GET' => 0], null, false, true, null]],
        943 => [[['_route' => 'app_reservation_edit', '_controller' => 'App\\Controller\\ReservationController::edit'], ['idReservation'], ['GET' => 0, 'POST' => 1], null, false, false, null]],
        951 => [
            [['_route' => 'app_reservation_delete', '_controller' => 'App\\Controller\\ReservationController::delete'], ['idReservation'], ['POST' => 0], null, false, true, null],
            [null, null, null, null, false, false, 0],
        ],
    ],
    null, // $checkCondition
];
