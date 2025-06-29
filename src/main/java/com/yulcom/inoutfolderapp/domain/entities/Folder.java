package com.yulcom.inoutfolderapp.domain.entities;

import com.yulcom.inoutfolderapp.domain.enums.Currency;
import com.yulcom.inoutfolderapp.domain.enums.FolderType;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Folders")
@Entity
@ToString
public class Folder extends BaseEntity
{
    private FolderType type;
    private Currency currency;
    private BigDecimal totalAmount;


    @OneToMany(
        fetch = FetchType.LAZY,
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )
    private List<Product> products  = new ArrayList<>();

    @OneToMany(
        fetch = FetchType.LAZY,
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )
    private List<File> files = new ArrayList<>();


    @ManyToOne(fetch = FetchType.LAZY)
    private CorporateUser createdBy;


    public void setProducts(List<Product> products)
    {
        this.products = products;
        this.totalAmount = calculateTotalAmount();
        this.updatedAt = LocalDateTime.now();
    }


    private BigDecimal calculateTotalAmount()
    {
        if (products == null || products.isEmpty())
        {
            return BigDecimal.ZERO;
        }
        return products
            .stream()
            .map(product -> product.getUnitValue().multiply(new BigDecimal(product.getQuantity())))
            .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
