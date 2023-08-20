package com.miko.assemblyLanguage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class AssemblyApplication {

	public static void main(String[] args) {

		SpringApplication.run(AssemblyApplication.class, args);
	}

}

@RestController
class AssemblyController {
	private final Map<String, Integer> programResults = new HashMap<>();

	@PostMapping("/execute")
	public Map<String, Boolean> executeAssembly(@RequestBody Map<String, String> requestBody) {
		String programId = requestBody.get("program_id");
		String assemblyProgram = requestBody.get("assembly_program");

		AssemblyInterpreter interpreter = new AssemblyInterpreter();
		interpreter.execute(assemblyProgram);

		int result = interpreter.getResult();
		programResults.put(programId, result);

		return Map.of("success", true);
	}
}
