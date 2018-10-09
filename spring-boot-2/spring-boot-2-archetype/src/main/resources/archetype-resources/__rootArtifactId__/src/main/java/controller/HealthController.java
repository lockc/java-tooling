#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.controller;

import org.springframework.stereotype.Controller;

import ${package}.config.EnvironmentSettings;

/**
 * Controller for exposing details about the health of the service
 */
@Controller
public class HealthController {

    private EnvironmentSettings environmentSettings;

    public HealthController( EnvironmentSettings environmentSettings ) {
        this.environmentSettings = environmentSettings;
    }
}