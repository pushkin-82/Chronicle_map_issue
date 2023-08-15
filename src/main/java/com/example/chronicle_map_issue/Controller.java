package com.example.chronicle_map_issue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
public class Controller {

  private final ChronicleMapExampleDtoService transactionDtoRepository;

  @PostMapping()
  public ResponseEntity<?> testEndpoint() {
    var uuid = UUID.randomUUID();
    transactionDtoRepository.saveExampleDtos(uuid, getListOfDtos());
    return ResponseEntity.ok().build();
  }

  private Collection<ExampleDto> getListOfDtos() {
    var resultList = new ArrayList<ExampleDto>();
    for (int i = 1; i <= 10; i++) {
      resultList.add(new ExampleDto(i));
    }

    return resultList;
  }
}
