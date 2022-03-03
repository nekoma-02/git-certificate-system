package com.epam.esm;

import com.epam.esm.config.SpringConfig;
import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.entity.Pagination;
import com.epam.esm.entity.Tag;
import com.epam.esm.impl.GiftCertificateRepositoryImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.*;

@SpringBootTest
@SpringJUnitConfig(SpringConfig.class)
@EnableAutoConfiguration
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@Transactional
@TestPropertySource(locations = "classpath:application.properties")
public class GiftCertificateRepositoryTest {

    private List<GiftCertificate> certificateList;
    private GiftCertificate certificate1;
    private GiftCertificate certificate2;
    private GiftCertificate certificate3;
    private Pagination pagination;
    private ZonedDateTime dateTime = ZonedDateTime.parse("2020-10-10T10:10:10+03:00[Europe/Moscow]");

    @Autowired
    private GiftCertificateRepository repo;

    @BeforeEach
    public void setUp() {
        pagination = new Pagination(5,0);
        certificate3 = GiftCertificate.builder()
                .name("name3")
                .price(BigDecimal.valueOf(12.2))
                .description("desc3")
                .createDate(dateTime.toLocalDateTime())
                .createDateTimeZone(dateTime.getZone())
                .lastUpdateDate(dateTime.toLocalDateTime())
                .lastUpdateDateTimeZone(dateTime.getZone())
                .duration(12)
                .tagSet(new HashSet<>(Arrays.asList(new Tag(1, "rock"), new Tag(2, "music"))))
                .build();
        certificate1 = GiftCertificate.builder()
                .name("name1")
                .price(BigDecimal.valueOf(12.2))
                .description("desc1")
                .createDate(dateTime.toLocalDateTime())
                .createDateTimeZone(dateTime.getZone())
                .lastUpdateDate(dateTime.toLocalDateTime())
                .lastUpdateDateTimeZone(dateTime.getZone())
                .duration(12)
                .tagSet(new HashSet<>(Arrays.asList(new Tag(1, "rock"), new Tag(2, "music"))))
                .build();
        certificate2 = GiftCertificate.builder()
                .name("name2")
                .price(BigDecimal.valueOf(10.2))
                .description("desc2")
                .createDate(dateTime.toLocalDateTime())
                .createDateTimeZone(dateTime.getZone())
                .lastUpdateDate(dateTime.toLocalDateTime())
                .lastUpdateDateTimeZone(dateTime.getZone())
                .duration(12)
                .tagSet(new HashSet<>(Arrays.asList(new Tag(1, "rock"))))
                .build();
        certificateList = Arrays.asList(certificate1, certificate2);
    }


    @Test
    void getCertificateList_whenCertificatesExists_thenReturnList() {
        List<GiftCertificate> certificateList2 = new ArrayList<>();
        Assertions.assertIterableEquals(certificateList2, repo.getAll(pagination));
    }

    @Test
    public void findById_whenCertificateExist_thenReturnOptionalCertificate() {
        Assertions.assertEquals(null, repo.findById(certificate1.getId()));
    }

    @Test
    public void findById_whenCertificateNotExists_thenReturnNull() {
        Assertions.assertEquals(null, repo.findById(99));
    }

    @Test
    public void create_whenCertificateDoesntExist_thenReturnTrue() {
        Assertions.assertEquals(Optional.of(certificate3),Optional.of(repo.create(certificate3)));
    }

    @Test
    public void create_whenCertificateIsNull_thenReturnNullPointerException() {
        //Assertions.assertThrows(NullPointerException.class, () -> repo.create(null));
    }

    @Test
    public void delete_whenCertificateExist_thenReturnTrue() {
        //Assertions.assertTrue(repo.delete(2));
    }


    @Test
    public void update_whenCertificateExist_thenReturnOptionalCertificate() {
       // certificate1.setName("Sport");
        //Assertions.assertEquals(certificate1.getName(), repo.update(certificate1).getName());
    }


}
