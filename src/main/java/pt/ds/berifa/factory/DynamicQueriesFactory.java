package pt.ds.berifa.factory;

import com.querydsl.core.BooleanBuilder;
import org.apache.commons.lang3.StringUtils;
import pt.ds.berifa.domain.QClient;
import pt.ds.berifa.dto.ClientForQueryingDto;

import java.util.Objects;

public class DynamicQueriesFactory {

    public static BooleanBuilder generateDynamicQuery(ClientForQueryingDto dto){
        BooleanBuilder expression = new BooleanBuilder();
        if(StringUtils.isNotBlank(dto.firstName())){
            expression.and(QClient.client.firstName.eq(dto.firstName()));
        }
        if(StringUtils.isNotBlank(dto.lastName())){
            expression.and(QClient.client.lastName.eq(dto.lastName()));
        }
        if(StringUtils.isNotBlank(dto.email())){
            expression.and(QClient.client.email.eq(dto.email()));
        }
        if(Objects.nonNull(dto.nif())){
            expression.and(QClient.client.nif.eq(dto.nif()));
        }
        if(Objects.nonNull(dto.id())){
            expression.and(QClient.client.id.eq(dto.id()));
        }
        if(StringUtils.isNotBlank(dto.phoneNumber())){
            expression.and(QClient.client.phoneNumber.eq(dto.phoneNumber()));
        }

        return expression;
    }
}
