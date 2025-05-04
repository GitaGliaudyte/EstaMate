package lt.vu.rest;

import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.Agent;
import lt.vu.persistence.AgentsDAO;
import lt.vu.rest.contracts.AgentDto;
import lt.vu.rest.contracts.AgentPropertyDto;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.OptimisticLockException;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
@Path("/agentDetails")
public class AgentsController {
    @Inject
    @Setter @Getter
    private AgentsDAO agentsDAO;

    @GET
    public String welcomingText() {
        return "Hello and welcome to EstaMate!";
    }

    @Path("/getall")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        List<Agent> agents = agentsDAO.loadAll();
        if (agents.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        List<AgentDto> agentDtos = agents.stream()
                .map(AgentDto::convertToAgentDto)
                .collect(Collectors.toList());

        return Response.ok(agentDtos).build();
    }

    @Path("/get{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") final Integer id) {
        Agent agent = agentsDAO.findById(id);
        if (agent == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        AgentDto agentDto = AgentDto.convertToAgentDto(agent);
        return Response.ok(agentDto).build();
    }

    @Path("/post")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response create(AgentPropertyDto agentPropertyDto) {
        Agent agent = new Agent();
        agent.setName(agentPropertyDto.getName());
        agent.setPhoneNumber(agentPropertyDto.getPhoneNumber());
        agent.setProperties(agentPropertyDto.getProperties());
        agentsDAO.persist(agent);
        return Response.ok(Response.Status.OK).build();
    }
    @Path("/put{id}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response update(@PathParam("id") final Integer id, AgentDto agentDto) {
        try {
            Agent agent = agentsDAO.findById(id);
            agent.setName(agentDto.getName());
            agentsDAO.update(agent);
            return Response.ok(AgentDto.convertToAgentDto(agent)).build();
        } catch (OptimisticLockException e) {
            return Response.status(Response.Status.CONFLICT).build();
        }
    }
}
