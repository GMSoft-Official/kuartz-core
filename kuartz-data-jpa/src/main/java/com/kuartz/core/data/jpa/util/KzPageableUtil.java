//package com.kuartz.core.data.jpa.util;
//
//import com.kuartz.core.common.domain.KzPage;
//import com.kuartz.core.common.domain.KzPageable;
//import com.kuartz.core.common.domain.KzSort;
//import com.kuartz.core.common.util.KzUtil;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.domain.Sort;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//public final class KzPageableUtil {
//
//    public KzPageableUtil() {
//        throw new UnsupportedOperationException();
//    }
//
//    public static PageRequest kzPageableToPageable(KzPageable kzPageable) {
//        return PageRequest.of(kzPageable.getPageNumber(), kzPageable.getPageSize(), kzSortToSort(kzPageable.getSort()));
//    }
//
//    public static Sort kzSortToSort(KzSort kzSort) {
//        if (kzSort != null && !KzUtil.isEmpty(kzSort.getKzOrderList())) {
//            List<Sort.Order> orderListesi = kzSort.getKzOrderList().parallelStream().map(
//                    kz -> new Sort.Order(kzDirectionToDirection(kz.getDirection()), kz.getProperty(),
//                                         kzNullHandlingToNullHandling(kz.getNullHandling()))).collect(Collectors.toList());
//
//            return Sort.by(orderListesi);
//        } else {
//            return Sort.unsorted();
//        }
//    }
//
//    public static Sort.Direction kzDirectionToDirection(KzSort.Direction direction) {
//        switch (direction) {
//            case ASC:
//                return Sort.Direction.ASC;
//            default:
//                return Sort.Direction.DESC;
//        }
//    }
//
//    public static Sort.NullHandling kzNullHandlingToNullHandling(KzSort.NullHandling nullHandling) {
//        if (!KzUtil.isNull(nullHandling)) {
//            switch (nullHandling) {
//                case NULLS_LAST:
//                    return Sort.NullHandling.NULLS_LAST;
//                case NULLS_FIRST:
//                    return Sort.NullHandling.NULLS_FIRST;
//                default:
//                    return Sort.NullHandling.NATIVE;
//            }
//        }
//        return Sort.NullHandling.NATIVE;
//    }
//
//    public static <T> KzPage<T> pageToKzPage(Page<T> page) {
//        return new KzPage<T>(page.getContent(), page.getTotalElements(), page.getTotalPages());
//    }
//
//    public static KzPageable pageableToKzPageable(Pageable pageable) {
//        return new KzPageable(sortToKzSort(pageable.getSort()), pageable.getPageNumber(), pageable.getPageSize());
//    }
//
//
//    public static KzSort sortToKzSort(Sort sort) {
//        List<Sort.Order> collect = sort.get().collect(Collectors.toList());
//        return new KzSort(orderToKzOrder(collect));
//    }
//
//    public static List<KzSort.KzOrder> orderToKzOrder(List<Sort.Order> orderList) {
//        return orderList.stream().map(
//                o -> new KzSort.KzOrder(nullHandlingToKzNullHandling(o.getNullHandling()), o.getProperty(),
//                                        directionToKzDirection(o.getDirection()))).collect(Collectors.toList());
//    }
//
//    public static KzSort.NullHandling nullHandlingToKzNullHandling(Sort.NullHandling nullHandling) {
//        switch (nullHandling) {
//            case NULLS_FIRST:
//                return KzSort.NullHandling.NULLS_FIRST;
//            case NULLS_LAST:
//                return KzSort.NullHandling.NULLS_LAST;
//            default:
//                return KzSort.NullHandling.DEFAULT;
//        }
//    }
//
//    public static KzSort.Direction directionToKzDirection(Sort.Direction direction) {
//        switch (direction) {
//            case DESC:
//                return KzSort.Direction.DESC;
//            case ASC:
//                return KzSort.Direction.ASC;
//        }
//        return null;
//    }
//}
