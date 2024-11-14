package com.github.vanily.infra.services;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.async.ResultCallback;
import com.github.dockerjava.api.command.ListContainersCmd;
import com.github.dockerjava.api.model.*;
import com.github.vanily.infra.domain.MinecraftContainer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Supplier;

@Service
@RequiredArgsConstructor
public class DockerService {

    private final DockerClient dockerClient;

    public String createContainer(String image, int port, List<String> env) {

        return dockerClient.createContainerCmd(image)
                .withHostConfig(HostConfig.newHostConfig()
                        .withNetworkMode("vanily_network")
                )
                .withEnv(env)
                .withTty(true)
                .withStdinOpen(true)
                .exec().getId();
    }

    public void startContainer(String id) {
        dockerClient.startContainerCmd(id).exec();
    }

    public void deleteContainer(String id) {
        dockerClient.removeContainerCmd(id).exec();
    }

    public void stopContainer(String id) {
        dockerClient.stopContainerCmd(id).exec();
    }

    public void restartContainer(String id) {
        dockerClient.restartContainerCmd(id).exec();
    }

    public List<MinecraftContainer> getContainers(List<String> networks) {

        final ListContainersCmd listContainersCmd = dockerClient.listContainersCmd()
                .withShowAll(true);

        if (!networks.isEmpty())
            listContainersCmd.withNetworkFilter(networks);

        return listContainersCmd
                .exec()
                .stream()
                .map(this::getContainer)
                .toList();

    }

    public Optional<MinecraftContainer> getContainer(String containerId) {
        return getContainers(List.of())
                .stream()
                .filter(container -> container.getId().equals(containerId))
                .findFirst();
    }

    public ResultCallback<Frame> getContainerLogs(String containerId, Supplier<StringBuilder> supplier) {

        final StringBuilder builder = supplier.get();

        return dockerClient.logContainerCmd(containerId)
                .withFollowStream(true)
                .withStdErr(true)
                .withStdOut(true)
                .withTimestamps(true)
                .exec(new ResultCallback.Adapter<>() {

                    @Override
                    public void onNext(Frame object) {

                        builder.append(new String(object.getPayload()));

                    }

                });

    }

    private MinecraftContainer getContainer(Container container) {

        Objects.requireNonNull(container.getNetworkSettings());

        return MinecraftContainer.builder()
                .id(container.getId())
                .hostname(container.getNetworkSettings().getNetworks().values().stream().map(ContainerNetwork::getIpAddress).filter(address -> address != null && !address.isEmpty()).findFirst().orElse(null))
                .name(container.getNames()[0].replace("/", ""))
                .build();

        /*ResultCallback.Adapter<Statistics> adapter = dockerClient.statsCmd(container.getId()).withNoStream(true).exec(new ResultCallback.Adapter<Statistics>() {

            @Override
            public void onNext(Statistics stats) {

                final long memoryLimit = stats.getMemoryStats().getLimit() == null ? 0L : stats.getMemoryStats().getLimit();
                final long usage = stats.getMemoryStats().getUsage() == null ? 0L : stats.getMemoryStats().getUsage();
                final StatsConfig statsConfig = stats.getMemoryStats().getStats();
                final long memory = usage - (statsConfig == null ? 0 : statsConfig.getCache() == null ? 0 : statsConfig.getCache());

                final double cpuDelta = stats.getCpuStats().getCpuUsage().getTotalUsage() - stats.getPreCpuStats().getCpuUsage().getTotalUsage();
                final double systemCpuDelta = (stats.getCpuStats().getSystemCpuUsage() == null ? 0 : stats.getCpuStats().getSystemCpuUsage()) - (stats.getPreCpuStats().getSystemCpuUsage() == null ? 0 : stats.getPreCpuStats().getSystemCpuUsage());
                final double numberCpu = stats.getCpuStats().getOnlineCpus() == null ? 0 : stats.getCpuStats().getOnlineCpus();
                final double cpuPercent = (cpuDelta / systemCpuDelta) * numberCpu * 100;


                builder
                        .memory(memory)
                        .cpu(cpuPercent == Double.NaN ? 0 : cpuPercent)
                        .memoryLimit(memoryLimit);

                try {
                    this.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }


            }

        });

        try {
            adapter.awaitCompletion();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return builder.build();*/
    }

}
