package com.epam.esm;

import com.epam.esm.config.SpringConfig;
import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.entity.Pagination;
import com.epam.esm.entity.Tag;
import com.epam.esm.exception.EntityNotFoundException;
import com.epam.esm.impl.GiftCertificateServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@ExtendWith(MockitoExtension.class)
@SpringJUnitConfig(SpringConfig.class)
@SpringBootTest
@EnableAutoConfiguration
@TestPropertySource(locations = "classpath:application.properties")
public class GiftCertificateServiceTest {
    private List<GiftCertificate> certificateList;
    private GiftCertificate certificate1;
    private GiftCertificate certificate2;
    private Pagination pagination;
    private ZonedDateTime dateTime;

    @Mock
    private GiftCertificateRepository repo;

    @InjectMocks
    private GiftCertificateService service = new GiftCertificateServiceImpl();

    @BeforeEach
    public void setUp() {
        dateTime = ZonedDateTime.now();
        pagination = new Pagination(5,0);
        certificate1 = GiftCertificate.builder()
                .id(1)
                .name("Sport")
                .price(BigDecimal.valueOf(12.2))
                .description("desc1")
                .createDate(dateTime.toLocalDateTime())
                .createDateTimeZone(dateTime.getZone())
                .lastUpdateDate(dateTime.toLocalDateTime())
                .lastUpdateDateTimeZone(dateTime.getZone())
                .duration(12)
                .tagSet(new HashSet<>(Arrays.asList(new Tag(1, "#rock"), new Tag(2, "#travel"))))
                .build();
        certificate2 = GiftCertificate.builder()
                .id(1)
                .name("chinese tower")
                .price(BigDecimal.valueOf(10.2))
                .description("desc1")
                .createDate(dateTime.toLocalDateTime())
                .createDateTimeZone(dateTime.getZone())
                .lastUpdateDate(dateTime.toLocalDateTime())
                .lastUpdateDateTimeZone(dateTime.getZone())
                .duration(12)
                .tagSet(new HashSet<>(Arrays.asList(new Tag(1, "#relax"), new Tag(2, "#geil"))))
                .build();
        certificateList = Arrays.asList(certificate1, certificate2);
    }

    @Test
    public void getAllCertificates() {
        Mockito.when(repo.getAll(pagination)).thenReturn(certificateList);
        Assertions.assertIterableEquals(certificateList, service.getAll(pagination));
    }

    @Test
    public void findById_whenCertificateExist_thenReturnCertificate() {
        Mockito.when(repo.findById(1)).thenReturn(certificate1);
        Assertions.assertEquals(certificate1, service.findById(1));
    }

    @Test
    public void findById_whenCertificateNotExists_theNotFoundException() {
        Mockito.when(repo.findById(0)).thenReturn(null);
        Assertions.assertThrows(EntityNotFoundException.class, () -> service.findById(0));
    }



}
