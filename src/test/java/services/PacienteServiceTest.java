package services;

import com.wallaceartur.GerenciamentoConsulta.model.Paciente;
import com.wallaceartur.GerenciamentoConsulta.model.dtos.PacienteDTO;
import com.wallaceartur.GerenciamentoConsulta.model.repositories.PacienteRepository;
import com.wallaceartur.GerenciamentoConsulta.services.PacienteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class) // Habilita o Mockito no JUnit 5
class PacienteServiceTest {

    @InjectMocks
    private PacienteService pacienteService; // Classe que estamos testando

    @Mock
    private PacienteRepository pacienteRepository; // Simulação do repositório

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveRetornarPacienteQuandoExistir() {
        // Criação do mock de Paciente
        Paciente pacienteMock = new Paciente();
        pacienteMock.setId(1L);
        pacienteMock.setNome("Wallace");
        pacienteMock.setEmail("wallace@gmail.com");

        // Simulação do repositório retornando o mock
        when(pacienteRepository.findById(1L)).thenReturn(Optional.of(pacienteMock));

        // Execução do método a ser testado
        PacienteDTO pacienteDTO = pacienteService.findById(1L);

        // Validações
        assertNotNull(pacienteDTO);
        assertEquals("Wallace", pacienteDTO.nome());
        assertEquals("wallace@gmail.com", pacienteDTO.email());

        // Verificação da chamada do método
        verify(pacienteRepository, times(1)).findById(1L);
    }
}
