package com.example.chronicle_map_issue;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.openhft.chronicle.map.ChronicleMap;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class ChronicleMapExampleDtoService {
  public static final String SHARED_FILES_DIRECTORY = "/tmp/";

  private final ExampleDto UNIFIED_BASE_TRANSACTION_DTO_PLACEHOLDER = ExampleDto.builder().build();

  public void saveExampleDtos(UUID requestId, Collection<ExampleDto> exampleDtos) {
    var averageBySizeTransaction =
        exampleDtos.stream()
            .findFirst()
            .orElse(
                exampleDtos.stream().findFirst().orElse(UNIFIED_BASE_TRANSACTION_DTO_PLACEHOLDER));
    File tmpFile = new File(SHARED_FILES_DIRECTORY + requestId + ".dat");
    int NUMBER_OF_ENTRIES_PLACEHOLDER = 1;
    try (ChronicleMap<Integer, ExampleDto> exampleDtoChronicleMap =
        ChronicleMap.of(Integer.class, ExampleDto.class)
            .name(requestId.toString())
            .averageValue(averageBySizeTransaction)
            .entries(exampleDtos.isEmpty() ? NUMBER_OF_ENTRIES_PLACEHOLDER : exampleDtos.size())
            .createPersistedTo(tmpFile)) {
      exampleDtos.forEach(t -> exampleDtoChronicleMap.put(t.getTransactionId(), t));
    } catch (IOException e) {
      log.error(e.getMessage());
    }
  }
}
