<?php

namespace App\Repository;

use App\Entity\User;
use Doctrine\Bundle\DoctrineBundle\Repository\ServiceEntityRepository;
use Doctrine\Persistence\ManagerRegistry;

/**
 * @extends ServiceEntityRepository<User>
 */
class UserRepository extends ServiceEntityRepository
{
    public function __construct(ManagerRegistry $registry)
    {
        parent::__construct($registry, User::class);
    }

    //    /**
    //     * @return User[] Returns an array of User objects
    //     */
    //    public function findByExampleField($value): array
    //    {
    //        return $this->createQueryBuilder('u')
    //            ->andWhere('u.exampleField = :val')
    //            ->setParameter('val', $value)
    //            ->orderBy('u.id', 'ASC')
    //            ->setMaxResults(10)
    //            ->getQuery()
    //            ->getResult()
    //        ;
    //    }

    //    public function findOneBySomeField($value): ?User
    //    {
    //        return $this->createQueryBuilder('u')
    //            ->andWhere('u.exampleField = :val')
    //            ->setParameter('val', $value)
    //            ->getQuery()
    //            ->getOneOrNullResult()
    //        ;
    //    }
    // src/Repository/UserRepository.php

public function countByGenre(string $genre): int
{
    return (int) $this->createQueryBuilder('u')
        ->select('COUNT(u.id)')
        ->where('LOWER(u.genre) = :genre')
        ->setParameter('genre', strtolower($genre))
        ->getQuery()
        ->getSingleScalarResult();
}

public function countUsersByGenre(): array
{
    $qb = $this->createQueryBuilder('u')
        ->select('u.genre, COUNT(u.id) as total')
        ->groupBy('u.genre');

    return $qb->getQuery()->getResult(); // Retourne un array : [ ['genre' => 'Homme', 'total' => 5], ... ]
}


public function countNewThisMonth(): int
{
    $startDate = new \DateTime('first day of this month');
    $endDate = new \DateTime('last day of this month');
    
    return (int) $this->createQueryBuilder('u')
        ->select('COUNT(u.id)')
        ->where('u.createdAt IS NOT NULL')
        ->andWhere('u.createdAt BETWEEN :start AND :end')
        ->setParameter('start', $startDate)
        ->setParameter('end', $endDate)
        ->getQuery()
        ->getSingleScalarResult();
}

public function countAllUsers(): int
{
    return (int) $this->createQueryBuilder('u')
        ->select('COUNT(u.id)')
        ->getQuery()
        ->getSingleScalarResult();
}

// EventRepository.php
public function findUpcomingEvents(int $limit = 3): array
{
    return $this->createQueryBuilder('e')
        ->where('e.startDate > :now')
        ->setParameter('now', new \DateTime())
        ->orderBy('e.startDate', 'ASC')
        ->setMaxResults($limit)
        ->getQuery()
        ->getResult();
}

public function getTicketStatsByEvent(): array
{
    return $this->createQueryBuilder('e')
        ->select('e.name as event, COUNT(t.id) as count')
        ->leftJoin('e.tickets', 't')
        ->groupBy('e.id')
        ->getQuery()
        ->getResult();
}

// TicketRepository.php
public function countThisMonth(): int
{
    $startDate = new \DateTime('first day of this month');
    $endDate = new \DateTime('last day of this month');
    
    return $this->count([
        'createdAt' => [
            'start' => $startDate,
            'end' => $endDate
        ]
    ]);
}

public function calculateTotalRevenue(): float
{
    return $this->createQueryBuilder('t')
        ->select('SUM(t.price)')
        ->getQuery()
        ->getSingleScalarResult() ?? 0;
}
}
