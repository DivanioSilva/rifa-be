package pt.ds.berifa.factory;

import com.querydsl.core.BooleanBuilder;
import org.apache.commons.lang3.StringUtils;
import pt.ds.berifa.domain.QClient;
import pt.ds.berifa.domain.QPartner;
import pt.ds.berifa.domain.QPrize;
import pt.ds.berifa.dto.ClientForQueryingDto;
import pt.ds.berifa.dto.PartnerForQueryingDto;
import pt.ds.berifa.dto.PrizeForQueryingDto;

import java.util.Objects;

public class DynamicQueriesFactory {

    private DynamicQueriesFactory() {
    }

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

    public static BooleanBuilder generateDynamicQuery(PartnerForQueryingDto dto){
        BooleanBuilder expression = new BooleanBuilder();
        if(StringUtils.isNotBlank(dto.email())){
            expression.and(QPartner.partner.email.eq(dto.email()));
        }
        if(StringUtils.isNotBlank(dto.address())){
            expression.and(QPartner.partner.address.eq(dto.address()));
        }
        if(StringUtils.isNotBlank(dto.phoneNumber())){
            expression.and(QPartner.partner.phoneNumber.eq(dto.phoneNumber()));
        }
        if(StringUtils.isNotBlank(dto.firstName())){
            expression.and(QPartner.partner.firstName.eq(dto.firstName()));
        }

        if(StringUtils.isNotBlank(dto.lastName())){
            expression.and(QPartner.partner.lastName.eq(dto.lastName()));
        }
        if(dto.nif() <= 0){
            expression.and(QPartner.partner.nif.eq(dto.nif()));
        }
        if(dto.id() != null && dto.id() <= 0){
            expression.and(QPartner.partner.id.eq(dto.id()));
        }
        if(StringUtils.isNotBlank(dto.address())){
            expression.and(QPartner.partner.address.eq(dto.address()));
        }
        return expression;
    }

    public static BooleanBuilder generateDynamicQuery(PrizeForQueryingDto dto){
        BooleanBuilder expression = new BooleanBuilder();
        expression.and(QPrize.prize.sorteado.eq(dto.sorteado()));
        return expression;
    }
}
