package lockc.spring.examples.domain;

import java.util.Set;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class ExampleEntity
{

    private Set<String> serviceProviders;

    private Set<String> roles;

    public Set<String> getServiceProviders()
    {

        return serviceProviders;
    }

    public void setServiceProviders(Set<String> serviceProviders)
    {

        this.serviceProviders = serviceProviders;
    }

    public Set<String> getRoles()
    {

        return roles;
    }

    public void setRoles(Set<String> roles)
    {

        this.roles = roles;
    }

    @Override
    public String toString()
    {
        return new ToStringBuilder(this).append("serviceProviders", serviceProviders).append("roles", roles).toString();
    }
}
